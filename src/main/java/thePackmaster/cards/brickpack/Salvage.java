package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Salvage extends AbstractBrickCard {
    public final static String ID = makeID(Salvage.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 1;

    public Salvage() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                int count = magicNumber;
                ArrayList<AbstractCard> discardList = new ArrayList<>();
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (AbstractCard c : adp().drawPile.group)
                    group.addToBottom(c);
                for (AbstractCard c : group.group) {
                    if (c.cost == -2) {
                        count--;
                        discardList.add(c);
                    }
                    if (count < 1)
                        break;
                }

                for (AbstractCard c :discardList)
                    adp().drawPile.moveToDiscardPile(c);

                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
