package thePackmaster.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.getRandomPackFromAll;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMCollection extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMCollection");

    public PMCollection() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        for (String s:
                getRandomPackFromAll().getCards() ) {
            RewardItem r = new RewardItem();
            r.cards.clear();
            r.cards.add(CardLibrary.getCard(s).makeCopy());
            AbstractDungeon.getCurrRoom().addCardReward(r);
        }

        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }
}
