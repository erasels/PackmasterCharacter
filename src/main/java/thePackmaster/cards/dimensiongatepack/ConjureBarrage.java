package thePackmaster.cards.dimensiongatepack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjureBarrage extends AbstractDimensionalCard {
    public final static String ID = makeID("ConjureBarrage");

    public ConjureBarrage() {
        super(ID, 0, CardRarity.UNCOMMON, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);

        baseMagicNumber = magicNumber = 3;
        setFrame("conjurebarrageframe.png");
        exhaust = true;
        cardsToPreview = new MagicMissile();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c;
        c = new MagicMissile();
        if (upgraded){
            c.upgrade();
        }
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        }

    }

    public void upp() {
        uDesc();
        cardsToPreview.upgrade();
    }
}