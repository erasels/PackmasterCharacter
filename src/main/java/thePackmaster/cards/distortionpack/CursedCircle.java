package thePackmaster.cards.distortionpack;

import basemod.Pair;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.BetterPowerNegationCheckPatch;
import thePackmaster.vfx.distortionpack.DarkCirclesEffect;

import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class CursedCircle extends AbstractPackmasterCard {
    public final static String ID = makeID("CursedCircle");
    // intellij stuff skill, all_enemy, uncommon, , , , , , 

    public CursedCircle() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = 0;
        for (AbstractCard c : p.hand.group) {
            if (c != this)
                ++amt;
        }

        if (amt > 0) {
            List<Pair<ApplyPowerAction, AbstractCreature>> targetMap = new ArrayList<>();

            atb(new VFXAction(new DarkCirclesEffect(p, amt * 2)));

            int finalAmt = amt;
            debuffTarget(p, p, finalAmt, targetMap);

            forAllMonstersLiving((mo)->{
                debuffTarget(p, mo, finalAmt, targetMap);
            });

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
    }

    private void debuffTarget(AbstractPlayer p, AbstractCreature target, int amount, List<Pair<ApplyPowerAction, AbstractCreature>> targetMap) {
        ApplyPowerAction a = new ApplyPowerAction(target, p, new StrengthPower(target, -amount));
        atb(a);
        targetMap.add(new Pair<>(a, target));
    }

    public void upp() {
        exhaust = false;
    }
}