package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.JediUtil;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Mimicry extends AbstractCreativityCard {

    public final static String ID = makeID(Mimicry.class.getSimpleName());

    public Mimicry() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard origin = this;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> list = new ArrayList<>();
                for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks)
                {
                    list.addAll(pack.cards);
                }
                list.removeIf(c -> c.rarity != CardRarity.COMMON);
                CardGroup tmpGrp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmpGrp.group = list;
                addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(tmpGrp), c -> {if (origin.upgraded) c.upgrade();}, false));
                isDone = true;
            }
        });
    }
}
