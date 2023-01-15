package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sliiiiither extends AbstractPackmasterCard {

    public final static String ID = makeID("Sliiiiither");

    public Sliiiiither() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Probably being overly careful about magicNumber modification, but you never know
        int amount;
        if (magicNumber < 3) {
            amount = AbstractDungeon.cardRandomRng.random(magicNumber, 3);
        } else {
            amount = magicNumber;
        }
        if(amount > 0)
            addToBot(new ApplyPowerAction(p,p, new DrawCardNextTurnPower(p,amount)));
    }

    public void upp() {
        if (magicNumber < 3) {
            upgradeMagicNumber(1);
        }
    }
}
