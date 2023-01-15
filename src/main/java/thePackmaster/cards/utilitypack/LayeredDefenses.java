package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.HashSet;

public class LayeredDefenses extends AbstractUtilityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("LayeredDefenses");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_BLOCK = 1;

    public LayeredDefenses() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < getAmount(); i++) {
                    this.addToTop(new GainBlockAction(p, block));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0].replace("{0}", this.getAmount() + "");
        this.initializeDescription();
    }

    public int getAmount() {
        HashSet<CardType> cardTypes = new HashSet<>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c != this) {
                cardTypes.add(c.type);
            }
        }
        return cardTypes.size();
    }
}
