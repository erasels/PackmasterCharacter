package thePackmaster.cards.entropypack;

import basemod.Pair;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.BetterPowerNegationCheckPatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Collapse extends AbstractPackmasterCard {
    public final static String ID = makeID("Collapse");
    // intellij stuff skill, self, uncommon, , , , , 4, 3

    public Collapse() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = this.magicNumber;
        ApplyPowerAction strength = new ApplyPowerAction(p, p, new StrengthPower(p, amt));
        atb(strength);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (BetterPowerNegationCheckPatch.Field.appliedSuccess.get(strength)) {
                    att(new ApplyPowerAction(p, p, new LoseStrengthPower(p, amt)));
                }
            }
        });
        applyToSelf(new VulnerablePower(p, 1, false));
    }

    @Override
    public void triggerOnManualDiscard() {
        int amt = this.magicNumber;
        if (amt > 0) {
            List<Pair<ApplyPowerAction, AbstractCreature>> targetMap = new ArrayList<>();

            AbstractPlayer p = AbstractDungeon.player;
            prepBuff(p, p, amt, targetMap);

            forAllMonstersLiving((mo)->{
                prepBuff(p, mo, amt, targetMap);
                att(new ApplyPowerAction(mo, p, new VulnerablePower(mo, 1, false)));
            });
            att(new ApplyPowerAction(p, p, new VulnerablePower(p, 1, false)));

            Collections.reverse(targetMap);

            att(new AbstractGameAction() {
                @Override
                public void update() {
                    for (Pair<ApplyPowerAction, AbstractCreature> applied : targetMap) {
                        if (BetterPowerNegationCheckPatch.Field.appliedSuccess.get(applied.getKey())) {
                            att(new ApplyPowerAction(applied.getValue(), p, new LoseStrengthPower(applied.getValue(), amt)));
                        }
                    }
                    this.isDone = true;
                }
            });

            for (Pair<ApplyPowerAction, AbstractCreature> notYetApplied : targetMap) {
                att(notYetApplied.getKey());
            }
        }
    }

    private void prepBuff(AbstractPlayer p, AbstractCreature target, int amount, List<Pair<ApplyPowerAction, AbstractCreature>> targetMap) {
        ApplyPowerAction a = new ApplyPowerAction(target, p, new StrengthPower(target, amount));
        targetMap.add(new Pair<>(a, target));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}