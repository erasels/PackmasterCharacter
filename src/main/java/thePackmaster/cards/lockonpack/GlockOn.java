package thePackmaster.cards.lockonpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.cardmodifiers.lockonpack.GlockOnModifier;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GlockOn extends AbstractLockonCard {

    public final static String ID = makeID(GlockOn.class.getSimpleName());

    public GlockOn() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        CardModifierManager.addModifier(this, new GlockOnModifier());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, damageTypeForTurn, EnumPatch.HERMIT_GUN);
    }
}
