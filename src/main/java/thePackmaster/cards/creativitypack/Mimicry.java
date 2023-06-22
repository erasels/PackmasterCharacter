package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.packs.CreativityPack;
import thePackmaster.util.Wiz;
import thePackmaster.util.creativitypack.JediUtil;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                ArrayList<AbstractCard> list = Wiz.getCardsMatchingPredicate(c -> c.rarity == CardRarity.COMMON && !CreativityPack.ID.equals(SpireAnniversary5Mod.cardParentMap.getOrDefault(c.cardID, null)) && !c.hasTag(CardTags.HEALING));
                if (list.isEmpty()) {
                    isDone = true;
                    return;
                }

                CardGroup tmpGrp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmpGrp.group = list.stream().map(c -> {
                    AbstractCard o = c.makeCopy();
                    if (upgraded) {
                        o.upgrade();
                    }
                    return o;
                }).collect(Collectors.toCollection(ArrayList::new));
                addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(tmpGrp), false));
                isDone = true;
            }
        });
    }
}
