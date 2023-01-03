package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MAXPOWER extends AbstractPackmasterCard {
    public final static String ID = makeID("MAXPOWER");

    public MAXPOWER() {
        super(ID, 4, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = 11;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID))
        {
            int str = this.baseMagicNumber - AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;

            if (str > 0)
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, str), str));
        }
        else{
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        upgradeBaseCost(3);
    }
}
