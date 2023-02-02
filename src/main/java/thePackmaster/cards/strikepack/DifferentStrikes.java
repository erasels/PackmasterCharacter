package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.Strike;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DifferentStrikes extends AbstractStrikePackCard {
    public final static String ID = makeID("DifferentStrikes");

    public DifferentStrikes() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int count=0;
        for (AbstractCard c: p.hand.group
             ) {
            if (c!=this) {
                addToBot(new ExhaustSpecificCardAction(c, p.hand));
                count++;
            }

        }
        for (int i = 0; i < count; i++) {
            AbstractCard strike = Wiz.returnTrulyRandomPrediCardInCombat(card -> card.hasTag(CardTags.STRIKE) && !card.hasTag(CardTags.HEALING) && (card.rarity == CardRarity.COMMON || card.rarity == CardRarity.UNCOMMON || card.rarity == CardRarity.RARE), true);
            if (strike != null) {
                strike.modifyCostForCombat(-1);
                addToBot(new MakeTempCardInHandAction(strike));
            } else { //Give a basic Strike if there were no Strikes in the pool somehow
                AbstractCard dumbStrike = new Strike();
                dumbStrike.modifyCostForCombat(-1);
                addToBot(new MakeTempCardInHandAction(dumbStrike));
            }

        }
    }

    public void upp() {
        this.upgradeBaseCost(1);
    }
}