package thePackmaster.powers.infestpack;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LordOfTheFliesPower extends AbstractPackmasterPower implements OnInfestPower {
    public static final String POWER_ID = makeID("LordOfTheFliesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LordOfTheFliesPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        AbstractDungeon.player.gameHandSize += amount;
    }

    @Override
    public void onRemove() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]) + DESCRIPTIONS[3] + (amount == 1 ? DESCRIPTIONS[4] : DESCRIPTIONS[5]);
    }

    @Override
    public void receiveInfest(AbstractCard infestingCard) {
        flash();
        InfestModifier.incrementInfestCount(infestingCard);
    }
}
