package thePackmaster.cards.creativitypack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cardmodifiers.creativitypack.DrawCardModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Souvenirs extends AbstractCreativityCard {

    public final static String ID = makeID(Souvenirs.class.getSimpleName());

    public Souvenirs() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);

    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i <= timesUpgraded; i++)
        {
            ArrayList<AbstractCard> list = new ArrayList<>();
            list.add(CardLibrary.getCard(Insight.ID).makeCopy());
            list.add(CardLibrary.getCard(Smite.ID).makeCopy());
            list.add(CardLibrary.getCard(Safety.ID).makeCopy());
            addToBot(new FlexibleDiscoveryAction(list,
                    selectedCard -> CardModifierManager.addModifier(selectedCard, new DrawCardModifier()),
                    false));
        }
    }
}
