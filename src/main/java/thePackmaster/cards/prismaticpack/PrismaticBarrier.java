package thePackmaster.cards.prismaticpack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class PrismaticBarrier extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PrismaticBarrier");
    private static final int COST = 1;
    private static final int BLOCK = 7;
    private static final int UPGRADE_BLOCK = 2;
    private static final int PLATED_ARMOR = 2;
    private static final int UPGRADE_PLATED_ARMOR = 1;

    public PrismaticBarrier() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = PLATED_ARMOR;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
        this.upgradeMagicNumber(UPGRADE_PLATED_ARMOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = this;
        this.addToBot(new GainBlockAction(p, this.block));
        if (this.playedDifferentColorCardCheck()) {
            this.addToBot(new ApplyPowerAction(p, p, new PlatedArmorPower(p, this.magicNumber)));
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractGameAction a : AbstractDungeon.actionManager.actions) {
                        if (a instanceof UseCardAction) {
                            if (ReflectionHacks.getPrivate(a, UseCardAction.class, "targetCard") == card) {
                                ((UseCardAction) a).exhaustCard = true;
                                break;
                            }
                        }
                    }
                    this.isDone = true;
                }
            });
        }
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
