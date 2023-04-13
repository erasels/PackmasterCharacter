package thePackmaster.powers.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class HammerThrowPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("HammerThrowPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public HammerThrowPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        int damage = amount;
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                boolean triggered = false;
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (c.selfRetain || c.retain) {
                            this.addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(damage, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE, true));
                            if(!triggered) {
                                flash();
                                triggered = true;
                            }
                        }
                    }
                    this.isDone = true;
            }
        });
    }

    public AbstractPower makeCopy() {
        return new HammerThrowPower(this.owner, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}




