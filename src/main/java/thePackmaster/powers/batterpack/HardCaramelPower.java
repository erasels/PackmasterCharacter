package thePackmaster.powers.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HardCaramelPower extends AbstractPackmasterPower{
    public AbstractCreature source;

    public static final String POWER_ID = makeID("HardCaramelPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public HardCaramelPower(final AbstractCreature owner, final int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractCreature realtarget = target;

        if (info.type == DamageInfo.DamageType.NORMAL) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!realtarget.hasPower(VulnerablePower.POWER_ID)) {
                        HardCaramelPower.this.flash();
                        Wiz.att(new ApplyPowerAction(realtarget, owner, new VulnerablePower(realtarget, 1, false), 1));
                    }
                    isDone = true;
                }
            });
        }

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
