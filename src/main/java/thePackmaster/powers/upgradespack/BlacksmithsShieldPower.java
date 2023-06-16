package thePackmaster.powers.upgradespack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlacksmithsShieldPower extends AbstractPackmasterPower {


    public static final String POWER_ID = makeID("BlacksmithsShieldPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public BlacksmithsShieldPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);

    }


    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.timesUpgraded>0) {
            addToBot(new GainBlockAction(AbstractDungeon.player,card.timesUpgraded*amount));
        } else if (card.timesUpgraded < 0) {
            addToBot(new GainBlockAction(AbstractDungeon.player,(-card.timesUpgraded)*amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
