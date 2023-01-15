package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.List;

public class GrabBag extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GrabBag");
    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int CARDS = 4;

    public GrabBag() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = CARDS;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                List<AbstractCard> cards = PrismaticUtil.getRandomDifferentColorCardInCombat(null, null, magicNumber);
                for (AbstractCard c : cards) {
                    this.addToTop(new MakeTempCardInHandAction(c));
                }
                this.isDone = true;
            }
        });
    }
}
