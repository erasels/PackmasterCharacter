package thePackmaster.rewards;

import basemod.abstracts.CustomReward;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.SpawnModificationCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.relics.PMBoosterBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                ArrayList<String> packs = ((PMBoosterBox)boosterBox).myPacks;
                // This provides rewards with only 1 card and we ignore all modifiers to the number of cards in rewards
                this.cards = SpireAnniversary5Mod.getCardsFromPacks(packs, 1, AbstractDungeon.cardRng);
                for (AbstractRelic relic : AbstractDungeon.player.relics) {
                    for (AbstractCard c : this.cards) {
                        relic.onPreviewObtainCard(c);
                    }
                }
                List<SpawnModificationCard> spawnModificationCards = this.cards.stream().filter(c -> c instanceof SpawnModificationCard).map(c -> (SpawnModificationCard)c).collect(Collectors.toList());
                for (SpawnModificationCard c : spawnModificationCards) {
                    c.onRewardListCreated(this.cards);
                }
            }
        }
    }

    public ArrayList<AbstractCard> getCards() {
        return cards;
    }
}
