package thePackmaster.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.*;

public class BanishingDecree extends AbstractPackmasterRelic implements CustomSavable<ArrayList<String>> {
    public static final String ID = makeID("BanishingDecree");
    public String bannedPack = null;
    public String newPack = null;
    private boolean cardSelected = true;
    private boolean cardSelected2 = true;

    public BanishingDecree() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public ArrayList<String> onSave() {
        ArrayList<String> saves = new ArrayList<>();
        saves.add(bannedPack);
        saves.add(newPack);
        return saves;
    }

    @Override
    public void onLoad(ArrayList<String> bannedPackID) {
        bannedPack = bannedPackID.get(0);
        newPack = bannedPackID.get(1);
        if (bannedPack != null) setDescriptionAfterLoading();
    }

    @Override
    public void onEquip() {
        cardSelected = false;
        cardSelected2 = false;
        if (AbstractDungeon.isScreenUp) {
            AbstractDungeon.dynamicBanner.hide();
            AbstractDungeon.overlayMenu.cancelButton.hide();
            AbstractDungeon.previousScreen = AbstractDungeon.screen;
        }
        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : SpireAnniversary5Mod.getPreviewCardsFromCurrentSet()) {
            tmp.addToTop(c);
        }
        AbstractDungeon.gridSelectScreen.open(tmp,
                1, DESCRIPTIONS[1] + name + ".",
                false, false, false, false);
    }


    @Override
    public void onUnequip() {
        if (bannedPack != null) {
            SpireAnniversary5Mod.currentPoolPacks.add(packsByID.get(bannedPack));
            CardCrawlGame.dungeon.initializeCardPools();
        }

    }


    @Override
    public void update() {
        super.update();
        if ((!cardSelected || !cardSelected2) & !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (!cardSelected) {
                cardSelected = true;
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractCardPack cp = Wiz.getPackByCard(card);
                bannedPack = cp.name;
                SpireAnniversary5Mod.logger.info("Banned Pack" + bannedPack);
                SpireAnniversary5Mod.currentPoolPacks.remove(cp);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();

                List<AbstractCard> allOtherPackPreviewCards = SpireAnniversary5Mod.getPreviewCardsNotFromCurrentSet();
                allOtherPackPreviewCards.removeIf(c -> c.cardID.equals(cp.previewPackCard.cardID));
                Collections.shuffle(allOtherPackPreviewCards, new Random(Settings.seed).random);
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (int i = 0; i < 5; i++) {
                    AbstractCard c = allOtherPackPreviewCards.get(i);
                    tmp.addToTop(c);
                }
                AbstractDungeon.gridSelectScreen.open(tmp,
                        1, DESCRIPTIONS[5] + name + ".",
                        false, false, false, false);
            } else {
                cardSelected2 = true;
                AbstractCard card = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                AbstractCardPack cp = Wiz.getPackByCard(card);
                newPack = cp.name;
                SpireAnniversary5Mod.logger.info("New Pack" + newPack);
                SpireAnniversary5Mod.currentPoolPacks.add(cp);
                CardCrawlGame.dungeon.initializeCardPools();

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                setDescriptionAfterLoading();

                if (AbstractDungeon.getCurrRoom() instanceof ShopRoom) {
                    AbstractDungeon.getCurrRoom().rewards.clear();
                }

                for (int i = 0; i < 3; i++) {
                    RewardItem reward = new RewardItem();
                    reward.cards = getCardsFromPacks(cp.packID, reward.cards.size(), AbstractDungeon.cardRng);
                    for (AbstractRelic relic : AbstractDungeon.player.relics) {
                        for (AbstractCard c : reward.cards) {
                            relic.onPreviewObtainCard(c);
                        }
                    }
                    AbstractDungeon.getCurrRoom().rewards.add(reward);
                }

                skipDefaultCardRewards = true;
                AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[6]);
                skipDefaultCardRewards = false;
                AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;

            }
        }
    }

    private void setDescriptionAfterLoading() {
        SpireAnniversary5Mod.logger.info("Banned Pack" + bannedPack);
        SpireAnniversary5Mod.logger.info("New Pack" + newPack);
        this.description = this.DESCRIPTIONS[2] + bannedPack + this.DESCRIPTIONS[3];
        this.description = this.description + " NL " + this.DESCRIPTIONS[2] + newPack + this.DESCRIPTIONS[4];
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
