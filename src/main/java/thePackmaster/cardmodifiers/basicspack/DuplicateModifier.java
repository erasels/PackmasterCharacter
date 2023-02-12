package thePackmaster.cardmodifiers.basicspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;

public class DuplicateModifier extends AbstractMadScienceModifier {

    public DuplicateModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (this.value == 1) {
            return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[0];
        } else return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[1] + this.value + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[2];
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if(card.purgeOnUse)
            return;
        for(int i = 0; i<this.value; i++){
            AbstractCard c = card.makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(c);
            c.target_x = Settings.WIDTH / 2.0F - 150 * this.value + 300 * i;
            c.target_y = Settings.HEIGHT / 2.0F;
            c.costForTurn = 0;
            if (target instanceof AbstractMonster) {
                c.calculateCardDamage((AbstractMonster) target);
                c.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, (AbstractMonster) target), true);
            } else {
                c.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, true), true);
            }
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DuplicateModifier(value);
    }
}