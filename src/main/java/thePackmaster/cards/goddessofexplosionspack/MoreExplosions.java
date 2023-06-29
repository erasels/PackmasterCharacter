package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoreExplosions extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("MoreExplosions");

    private static final int MAGIC = 1;
    private static final int MAGIC_UP = 1;

    public MoreExplosions() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // List of powers.
        List<AbstractCard> eligibleCards = Wiz.getCardsMatchingPredicate(c -> c.type == CardType.POWER && !c.hasTag(AbstractCard.CardTags.HEALING));
        List<AbstractCard> powerList = new ArrayList<>();

        // Add from eligible cards to power list.
        for(int hm = 0; hm < magicNumber; hm++) {
            if (eligibleCards.size() > 0)
                powerList.add(eligibleCards.get(AbstractDungeon.cardRandomRng.random(0, eligibleCards.size() - 1)).makeCopy());
        }

        // Draw.
        Wiz.atb(new DrawCardAction(magicNumber));

        // Shuffle powers into draw pile.
        for(AbstractCard c : powerList)
            Wiz.atb(new MakeTempCardInDrawPileAction(c, 1, true, true));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UP);
    }
}
