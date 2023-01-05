package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShedWeight extends AbstractPackmasterCard {
    public final static String ID = makeID("ShedWeight");
    UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");

    public ShedWeight() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        Wiz.discard(magicNumber);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractDungeon.handCardSelectScreen.selectedCards.group.stream().filter(c -> c.cost == -2).forEach(c -> Wiz.att(new DrawCardAction(1)));
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}
