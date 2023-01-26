package thePackmaster.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rewards.RewardItem;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.*;

public class PMCollection extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMCollection");

    public PMCollection() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, null, true);
    }

    @Override
    public void onEquip() {
        AbstractDungeon.getCurrRoom().rewards.clear();

        for (String s :
                getRandomPackFromAll(new Random(Settings.seed + 37)).getCards()) {
            if (CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.COMMON ||
                    CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.UNCOMMON ||
                    CardLibrary.getCard(s).rarity == AbstractCard.CardRarity.RARE) {

                RewardItem r = new RewardItem();
                r.cards.clear();
                r.cards.add(CardLibrary.getCard(s).makeCopy());
                r.cards.forEach(c -> Wiz.p().relics.forEach(rel -> rel.onPreviewObtainCard(c)));
                AbstractDungeon.getCurrRoom().addCardReward(r);
            }


        }

        skipDefaultCardRewards = true;
        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
        skipDefaultCardRewards = false;
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }
}
