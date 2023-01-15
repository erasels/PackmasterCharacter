package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class ExoticKnowledge extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ExoticKnowledge");
    private static final int COST = 1;
    private static final int DRAW = 2;
    private static final int EXTRA_DRAW = 2;
    private static final int UPGRADE_EXTRA_DRAW = 1;

    public ExoticKnowledge() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.secondMagic = this.baseSecondMagic = EXTRA_DRAW;
    }

    @Override
    public void upp() {
        this.upgradeSecondMagic(UPGRADE_EXTRA_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int draw = this.magicNumber;
        if (this.playedDifferentColorCardCheck()) {
            draw += this.secondMagic;
        }
        this.addToBot(new DrawCardAction(draw));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (this.playedDifferentColorCardCheck()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    private boolean playedDifferentColorCardCheck() {
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(c -> c.color != AbstractDungeon.player.getCardColor());
    }
}
