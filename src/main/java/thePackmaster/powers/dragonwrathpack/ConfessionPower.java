package thePackmaster.powers.dragonwrathpack;

import basemod.BaseMod;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.dragonwrathpack.HolyWrath;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.dragonwrathpack.DivineEyeParticle;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConfessionPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public AbstractCreature source;
    public static final String POWER_ID = makeID("confession");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public ConfessionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        // We load those textures here.
        this.loadRegion("mantra");

        updateDescription();
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        if (AbstractDungeon.actionManager.turnHasEnded){
            addToTop(new ReducePowerAction(owner,owner,this,amount/2));
        }
        Wiz.att(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        Wiz.att(new AbstractGameAction() {
            @Override
            public void update() {
                CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
                for (int i = 0; i < AbstractDungeon.miscRng.random(20, 30); ++i) {
                    AbstractDungeon.effectsQueue.add(new DivineEyeParticle());
                }
                isDone = true;
            }
        });
        return damageAmount;
    }
    @Override
    public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
        super.renderIcons(sb, x, y, Color.GOLD.cpy());
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new ConfessionPower(owner, amount);
    }
    @Override
    public void onInitialApplication() {
        for (AbstractCard c : AbstractDungeon.player.discardPile.group){
            if (c instanceof HolyWrath && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE){
                Wiz.att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.discardPile.removeCard(c);
                        AbstractDungeon.player.hand.addToTop(c);
                        isDone = true;
                    }
                });
            }
        }
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        for (AbstractCard c : AbstractDungeon.player.discardPile.group){
            if (c instanceof HolyWrath && AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE){
                Wiz.att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.player.discardPile.removeCard(c);
                        AbstractDungeon.player.hand.addToTop(c);
                        isDone = true;
                    }
                });
            }
        }
    }
}
