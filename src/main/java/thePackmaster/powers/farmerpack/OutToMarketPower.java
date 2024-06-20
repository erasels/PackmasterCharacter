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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OutToMarketPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("OutToMarketPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private String desc;

    private HashSet<AbstractCard.CardType> played = new HashSet<>();

    public OutToMarketPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        cleanUp();
        updateCardsPlayed(AbstractCard.CardType.POWER);
    }

    public void onAfterCardPlayed(AbstractCard card) {
        int count = SeasonalMethod(card);
        for(int j = 0; j < count; j++){
            updateCardsPlayed(card.type);
            flash();
            addToBot(new SFXAction("ATTACK_HEAVY"));
            if (Settings.FAST_MODE) {
                addToBot(new VFXAction(new CleaveEffect()));
            } else {
                addToBot(new VFXAction(this.owner, new CleaveEffect(), 0.2F));
            }
            description = desc;
            addToBot(new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        }
    }

    public void updateDescription() {
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.description = desc;
    }
        public int SeasonalMethod(AbstractCard c){
        int returnValue = 0;
        if (!AbstractDungeon.actionManager.cardsPlayedThisCombat.isEmpty() && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).type != c.type){
                returnValue = 1;}
        //SpireAnniversary5Mod.logger.info("Completing seasonal method at value  " + returnValue);
        return returnValue;}

    private void cleanUp() {
        played.clear();
        desc = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        this.description = desc;
    }

    private void updateCardsPlayed(AbstractCard.CardType type) {
        String add;
        cleanUp();
        switch (type) {
            case SKILL:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[3];
                break;
            case ATTACK:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[2];
                break;
            case POWER:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[4];
                break;
            case STATUS:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[5];
                break;
            case CURSE:
                add = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[6];
                break;
            default:
                add = type.name();
        }

        desc = desc + " " + add;
        description = desc;
    }
}
