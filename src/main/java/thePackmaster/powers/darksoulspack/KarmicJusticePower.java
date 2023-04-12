package thePackmaster.powers.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseTempHpPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class KarmicJusticePower extends AbstractPackmasterPower implements OnLoseTempHpPower {

    //DEPRECATED - NOT PLANNED FOR USE

    public static final String POWER_ID = makeID("KarmicJusticePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private final int count;


    public KarmicJusticePower(AbstractPlayer owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        count = amount;
        canGoNegative = true;
        updateDescription();
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        this.flash();
        triggerJustice();
    }

    @Override
    public int onLoseTempHp(DamageInfo info, int amount){
        this.flash();
        triggerJustice();
        return info.output;
    }

    private void triggerJustice(){
        this.reducePower(1);
        if (amount <= 0){
            amount = count;
            Wiz.att(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(20, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        }
    }


}