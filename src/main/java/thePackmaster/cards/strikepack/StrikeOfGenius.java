package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.strikepack.StrikeOfGeniusPower;
import thePackmaster.powers.strikepack.StrikeOfGeniusUpgradedPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeOfGenius extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeOfGenius");

    public StrikeOfGenius() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {


        if (upgraded) Wiz.applyToSelf(new StrikeOfGeniusUpgradedPower(p, 1));
        else
            Wiz.applyToSelf(new StrikeOfGeniusPower(p, 1));
    }


    public void upp() {
        upgradeMagicNumber(1);
    }
}