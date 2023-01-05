package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class LunaticRage extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LunaticRage");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int DRAW = 2;
    private static final int UPGRADE_DRAW = 1;
    private static final int ENERGY = 2;

    public LunaticRage() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new GainEnergyAction(ENERGY));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.type != CardType.ATTACK && c.type != CardType.STATUS) {
                        this.addToTop(new DiscardSpecificCardAction(c));
                    }
                }

                this.isDone = true;
            }
        });
    }
}
