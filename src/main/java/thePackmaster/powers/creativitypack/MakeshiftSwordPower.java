package thePackmaster.powers.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import thePackmaster.onGenerateCardMidcombatInterface;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MakeshiftSwordPower
        extends AbstractPackmasterPower
        implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = makeID(MakeshiftSwordPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MakeshiftSwordPower(AbstractCreature owner, int amount)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        loadRegion("thousandCuts");
    }

    @Override
    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onCreateCard(AbstractCard card)
    {
        addToTop(new ApplyPowerAction(owner, owner, new VigorPower(owner, amount)));
    }
}
