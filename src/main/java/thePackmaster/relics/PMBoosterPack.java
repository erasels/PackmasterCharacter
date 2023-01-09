package thePackmaster.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.ShopRoom;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.*;

public class PMBoosterPack extends AbstractPackmasterRelic {
    public static final String ID = makeID(PMBoosterPack.class.getSimpleName());

    public PMBoosterPack() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        RewardItem r = new RewardItem();
        r.cards.clear();

        if (AbstractDungeon.getCurrRoom() instanceof ShopRoom)
            AbstractDungeon.getCurrRoom().rewards.clear();

        System.out.println(getRandomPackFromAll().getCards().size());

        for (String s : getRandomPackFromAll().getCards())
            r.cards.add(CardLibrary.getCard(s).makeCopy());

        AbstractDungeon.getCurrRoom().addCardReward(r);

        skipDefaultCardRewards = true;
        AbstractDungeon.combatRewardScreen.open();
        skipDefaultCardRewards = false;

        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }
}
