package thePackmaster.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rewards.RewardItem;
import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.*;

public class PMBoosterPack extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMBoosterPack");

    public PMBoosterPack() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        RewardItem r = new RewardItem();
        r.cards.clear();

        for (String s:
                getRandomPackFromAll().getCards() ) {
            r.cards.add(CardLibrary.getCard(s).makeCopy());
        }

        AbstractDungeon.getCurrRoom().addCardReward(r);

        AbstractDungeon.combatRewardScreen.open(this.DESCRIPTIONS[1]);
        AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.0F;
    }
}
