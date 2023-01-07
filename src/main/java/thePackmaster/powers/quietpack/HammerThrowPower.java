package thePackmaster.powers.quietpack;


import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.EstablishmentPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class HammerThrowPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("HammerThrowPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;


    public HammerThrowPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
    }

    public void atEndOfTurn(boolean isPlayer) {
        int damage = amount;
        this.flash();
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (c.selfRetain || c.retain)
                            this.addToBot(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(damage, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE, true));
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




