package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Stall extends AbstractWardenCard {
    public final static String ID = makeID("Stall");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PutOnDeckAction");

    public Stall() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        Wiz.atb(new HandSelectAction(this.magicNumber, (c) -> true, list -> {
            for (AbstractCard c : list)
                p.hand.moveToDeck(c, false);
            AbstractDungeon.player.hand.refreshHandLayout();
            list.clear();
        }, null, uiStrings.TEXT[0],false,true,true));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}
