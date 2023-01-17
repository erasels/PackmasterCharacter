package thePackmaster.cards.metapack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Ambition extends AbstractMetaCard {
    public final static String ID = makeID("Ambition");

    public Ambition() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                tmp.group = (ArrayList<AbstractCard>) Wiz.p().drawPile.group.stream()
                        .filter(c -> c.type == CardType.POWER)
                        .collect(Collectors.toList());
                for (int i = 0; i < magicNumber; i++) {
                    if(!tmp.isEmpty() && Wiz.hand().size() < BaseMod.MAX_HAND_SIZE)
                    {
                        AbstractCard randomcard = tmp.getRandomCard(AbstractDungeon.cardRandomRng);
                        Wiz.p().drawPile.moveToHand(randomcard);
                        this.addToTop(new MakeTempCardInHandAction(randomcard.makeStatEquivalentCopy()));
                    }
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        this.isInnate = true;
    }
}
