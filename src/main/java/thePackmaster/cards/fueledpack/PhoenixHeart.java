package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PhoenixHeart extends AbstractFueledCard {
    public final static String ID = makeID(PhoenixHeart.class.getSimpleName());
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PhoenixHeart() {
        super(ID, COST, TYPE, RARITY, TARGET);
        cardsToPreview = new HotAsh();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = 0;
        for (AbstractCard c : adp().hand.group) {
            if (c instanceof HotAsh) {
                count++;
                atb(new ExhaustSpecificCardAction(c, adp().hand, true));
            }
        }
        for (int i = 0; i < count; i++)
            atb(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}
