package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sideboard extends AbstractPackmasterCard {
    public final static String ID = makeID("Sideboard");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Sideboard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new DiscardPileToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeBlock(3);
    }
}