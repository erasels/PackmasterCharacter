package thePackmaster.powers.farmerpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OutToMarketPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("OutToMarketPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
   private static boolean attack = false;
   private String desc;
   private static boolean skill = false;
   private static boolean power = false;
   private static boolean status = false;
   private static boolean curse = false;
   private static boolean noResult = false;
    public OutToMarketPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    public void atStartOfTurn(){
        attack = false;
        skill = false;
        power = false;
        status = false;
        curse = false;
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.description = desc;
    }
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && !attack)  {
            attack = true;
            desc = desc + " " + CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[2];
        }
        else if (card.type == AbstractCard.CardType.SKILL && !skill){
            skill = true;
            desc = desc + " " + CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[3];
        }
        else if (card.type == AbstractCard.CardType.POWER && !power){
            power = true;
            desc = desc + " " + CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[4];
        }
        else if (card.type == AbstractCard.CardType.STATUS && !status){
            status = true;
            desc = desc + " " + CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[5];
        }
        else if (card.type == AbstractCard.CardType.CURSE && !curse){
            curse = true;
            desc = desc + " " + CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[6];
        }
        else{
            noResult = true;
        }
        if (!noResult){
            this.flash();
            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new CleaveEffect()));
            } else {
                this.addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
            }
            this.description = desc;
            this.addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        }
    }

    public void updateDescription() {
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.description = desc;
    }
}
