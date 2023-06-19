package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.CreativityPack;
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
                ArrayList<AbstractCard> list = new ArrayList<>();
                for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
                    if (!CreativityPack.ID.equals(pack.packID)) {
                        list.addAll(pack.cards);
                    }
                }
                list.removeIf(c -> c.rarity != CardRarity.COMMON);
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
