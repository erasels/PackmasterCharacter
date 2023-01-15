package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BuildingChargePower  extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(BuildingChargePower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public BuildingChargePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        Wiz.applyToSelf(new ChargeUpPower(amount));
        AbstractDungeon.effectList.add(new FlashPowerEffect(this));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if(isPlayer)
            Wiz.removePower(this);
    }

    @Override
    public AbstractPower makeCopy() {
        return new ChargeUpPower(amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }

}




