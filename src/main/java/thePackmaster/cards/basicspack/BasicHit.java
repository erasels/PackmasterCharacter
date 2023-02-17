package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Strike;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicHit extends AbstractPackmasterCard {
    public final static String ID = makeID("BasicHit");

    public BasicHit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.cardsToPreview = new Strike();
        this.baseMagicNumber = this.magicNumber = 5;
        for(int i = 0; i<this.magicNumber;i++){
            this.cardsToPreview.upgraded = false;
            this.cardsToPreview.upgrade();
        }
        this.cardsToPreview.name = this.cardsToPreview.originalName + "+" + this.magicNumber;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        while(this.cardsToPreview.timesUpgraded>this.magicNumber)
            this.cardsToPreview = new Strike();
        while(this.cardsToPreview.timesUpgraded<this.magicNumber) {
            this.cardsToPreview.upgraded = false;
            this.cardsToPreview.upgrade();
        }
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview));
    }

    public void upp(){
        int oldMagic = this.magicNumber;
        upgradeMagicNumber(2);
        for(int i = oldMagic; i<this.magicNumber;i++){
            this.cardsToPreview.upgraded = false;
            this.cardsToPreview.upgrade();
        }
        this.cardsToPreview.name = this.cardsToPreview.originalName + "+" + this.cardsToPreview.timesUpgraded;
    }
}
