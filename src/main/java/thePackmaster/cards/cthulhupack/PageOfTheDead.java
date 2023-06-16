package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PageOfTheDead extends AbstractCthulhuCard {
    public final static String ID = makeID("PageOfTheDead");

    public PageOfTheDead() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        cardsToPreview = new Lunacy();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        loseSanity(magicNumber);
        AbstractCard strike = Wiz.returnTrulyRandomPrediCardInCombat(card -> card.type == CardType.ATTACK
                && !card.hasTag(CardTags.HEALING) && (card.cost == 2) && (card.rarity == CardRarity.COMMON || card.rarity == CardRarity.UNCOMMON || card.rarity == CardRarity.RARE), true);
        if (upgraded) strike.upgrade();
        addToBot(new MakeTempCardInHandAction(strike));
    }

    public void upp() {

    }
}