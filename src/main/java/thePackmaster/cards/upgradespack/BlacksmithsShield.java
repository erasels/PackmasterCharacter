package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.upgradespack.BlacksmithsShieldPower;

public class BlacksmithsShield extends AbstractPackmasterCard {

    public final static String ID = SpireAnniversary5Mod.makeID("BlacksmithsShield");

    public BlacksmithsShield() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new BlacksmithsShieldPower(AbstractDungeon.player, 1)));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
