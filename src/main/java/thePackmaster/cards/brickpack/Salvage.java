package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
                for (AbstractCard c : adp().drawPile.group) {
                    if (c.cost == -2) {
                        count--;
                        adp().drawPile.moveToDiscardPile(c);
                    }
                    if (count < 1)
                        break;
                }

                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
