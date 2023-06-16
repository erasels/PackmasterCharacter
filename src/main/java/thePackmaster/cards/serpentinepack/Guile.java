package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.serpentinepack.CunningStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Guile extends AbstractSerpentineCard {

    private static final int COST = 1;
    private static final int UPGRADE_MAGIC = 1;
    public final static String ID = makeID("Guile");
    private static final int MAGIC = 2;

    public Guile() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.cardsToPreview = new DirtyTrick();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new DiscardAction(abstractPlayer, abstractPlayer, 2, false));
        addToBot(new MakeTempCardInHandAction(new DirtyTrick()));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
