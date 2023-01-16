package thePackmaster.cards.alignmentpack;

import basemod.Pair;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.patches.BetterPowerNegationCheckPatch;
import thePackmaster.vfx.alignmentpack.FlashImageEffect;
import thePackmaster.vfx.distortionpack.DarkCirclesEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Quincunx extends AbstractAstrologerCard {
    public final static String ID = makeID("Quincunx");

    public Quincunx() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.magicNumber = this.baseMagicNumber = 5;
        isEthereal = true;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new VFXAction(new FlashImageEffect(this.portrait)));
        List<Pair<ApplyPowerAction, AbstractCreature>> targetMap = new ArrayList<>();

        AbstractPlayer p = AbstractDungeon.player;
        int finalAmt = this.magicNumber;
        forAllMonstersLiving((mo)->debuffTarget(p, mo, finalAmt, targetMap));

        Collections.reverse(targetMap);

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (Pair<ApplyPowerAction, AbstractCreature> applied : targetMap) {
                    if (BetterPowerNegationCheckPatch.Field.appliedSuccess.get(applied.getKey())) {
                        att(new ApplyPowerAction(applied.getValue(), p, new GainStrengthPower(applied.getValue(), finalAmt)));
                    }
                }
                this.isDone = true;
            }
        });
    }
    private void debuffTarget(AbstractPlayer p, AbstractCreature target, int amount, List<Pair<ApplyPowerAction, AbstractCreature>> targetMap) {
        ApplyPowerAction a = new ApplyPowerAction(target, p, new StrengthPower(target, -amount));
        atb(a);
        targetMap.add(new Pair<>(a, target));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}