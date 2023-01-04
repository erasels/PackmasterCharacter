package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.distortionpack.DarkCirclesEffect;

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
            atb(new VFXAction(new DarkCirclesEffect(p, amt * 2)));

            int finalAmt = amt;
            applyToSelf(new StrengthPower(p, -finalAmt));

            forAllMonstersLiving((mo)->{
                applyToEnemy(mo, new StrengthPower(mo, -finalAmt));
            });

            if (!p.hasPower(ArtifactPower.POWER_ID))
                applyToSelf(new GainStrengthPower(p, finalAmt));
            forAllMonstersLiving((mo)->{
                if (!mo.hasPower(ArtifactPower.POWER_ID))
                    applyToEnemy(mo, new GainStrengthPower(mo, finalAmt));
            });
        }
    }

    public void upp() {
        exhaust = false;
    }
}