package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.serpentinepack.CunningStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DirtyTrick extends AbstractSerpentineCard {

    private static final int COST = 0;
    public final static String ID = makeID("DirtyTrick");

    public DirtyTrick() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, CardColor.COLORLESS);
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new CunningStance()));
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }
}
