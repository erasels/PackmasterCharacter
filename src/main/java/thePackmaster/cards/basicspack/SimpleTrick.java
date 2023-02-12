package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Cardistry;
import thePackmaster.powers.basicspack.SimpleTrickPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SimpleTrick extends AbstractPackmasterCard {
    public final static String ID = makeID("SimpleTrick");

    public SimpleTrick() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new Cardistry();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MakeTempCardInHandAction(this.cardsToPreview));
        addToBot(new ApplyPowerAction(p, p, new SimpleTrickPower(this.magicNumber)));
    }

    public void upp(){
        upgradeMagicNumber(1);
        this.cardsToPreview.upgrade();
    }
}
