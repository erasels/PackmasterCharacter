package thePackmaster.cards.creativitypack.special;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.cards.creativitypack.AbstractCreativityCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@AutoAdd.Ignore
public class BrushModalTypeChoice extends AbstractCreativityCard {
    public final static String ID = makeID(BrushModalTypeChoice.class.getSimpleName());
    public final static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup");
    public BrushModalTypeChoice(CardType type) {
        super(ID, -2, type, CardRarity.SPECIAL, CardTarget.NONE);
        switch (type) {
            case ATTACK:
                name = uiStrings.TEXT[0];
                break;
            case SKILL:
                name = uiStrings.TEXT[1];
                break;
            case POWER:
                name = uiStrings.TEXT[2];
                break;
            default:
                name = uiStrings.TEXT[5];
                break;
        }
        initializeTitle();
    }

    public void onChoseThisOption()
    {
        addToBot(new EasyModalChoiceAction(generateList()));
    }

    public ArrayList<AbstractCard> generateList()
    {
        ArrayList<AbstractCard> retVal = new ArrayList<>();
        Set<Integer> costList = new HashSet<>();
        for (AbstractCard c : CardLibrary.getAllCards())
        {
            if (    c.type == type &&
                    !c.hasTag(CardTags.HEALING) &&
                    c.rarity != CardRarity.SPECIAL)
            {
                costList.add(c.cost);
            }
        }
        costList.stream().sorted().forEach(i ->
        {
            AbstractCard c = new BrushModalCostChoice(i, type);
            if (upgraded) c.upgrade();
            retVal.add(c);
        });
        return retVal;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void upp() {
    }
}
