package thePackmaster.rewards;

import basemod.abstracts.CustomReward;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.relics.PMBoosterBox;

import java.util.ArrayList;

public class PMBoosterBoxCardReward extends CustomReward {
    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("PMBoosterBoxCardReward")).TEXT;

    private ArrayList<AbstractCard> cards;

    public PMBoosterBoxCardReward() {
        super(ImageMaster.REWARD_CARD_NORMAL, TEXT[0], CustomRewardTypes.PACKMASTER_PMBOOSTERBOXCARD);
    }

    @Override
    public boolean claimReward() {
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[1]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        return false;
    }

    public void populateCards() {
        //We have this as a method that gets a delayed call (from a patch on CombatRewardScreen.open) because
        //immediately generating the cards in the constructor would make saving and loading after combat change
        //the cards you get (because we'd advance RNG the first time, save the advanced RNG, load, and generate
        //card rewards from the advanced RNG)
        if (this.cards == null) {
            AbstractRelic boosterBox = AbstractDungeon.player.getRelic(PMBoosterBox.ID);
            if (boosterBox != null) {
                RewardItem reward = new RewardItem();
                ArrayList<String> packs = ((PMBoosterBox)boosterBox).myPacks;
                int numCards = reward.cards.size();
                this.cards = SpireAnniversary5Mod.getCardsFromPacks(packs, numCards, AbstractDungeon.cardRng);
                for (AbstractRelic relic : AbstractDungeon.player.relics) {
                    for (AbstractCard c : this.cards) {
                        relic.onPreviewObtainCard(c);
                    }
                }
            }
        }
    }
}
