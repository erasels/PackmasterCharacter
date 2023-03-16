package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Vanguard extends AbstractIntrigueCard {
    public final static String ID = makeID("Vanguard");

    public Vanguard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (isPrecious(c)) {
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true, EnergyPanel.getCurrentEnergy(), true, true));
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}
