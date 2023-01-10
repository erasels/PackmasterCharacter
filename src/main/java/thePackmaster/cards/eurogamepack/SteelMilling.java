package thePackmaster.cards.eurogamepack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

import thePackmaster.powers.eurogamepack.EntranceTrackerPower;
import thePackmaster.powers.eurogamepack.VictoryPoints;

public class SteelMilling extends AbstractVPCard {
    public static final String ID = makeID("SteelMilling");

    public SteelMilling() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 20;
        //this.isMultiDamage = true;
        //this.basevp = vp = 8;


    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower(EntranceTrackerPower.POWER_ID)) {
            return super.canUse(p, m);
        } else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    public void upp() {
            this.upgradeDamage(8);
    }




}
