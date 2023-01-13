package thePackmaster.powers.rimworldpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.Random;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class MoodPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(MoodPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static UIStrings inspirationStrings = CardCrawlGame.languagePack.getUIString(modID + ":Inspirations");

    public MoodPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

        canGoNegative = true;
        if(amount < 0)
            type = PowerType.DEBUFF;
        else
            type = PowerType.BUFF;

        checkForBreaksAndInsps();
    }

    @Override
    public void onInitialApplication() {
        checkForBreaksAndInsps();
    }

    @Override
    public void stackPower(int stackAmount)
    {
        amount += stackAmount;

        checkForBreaksAndInsps();
    }

    private void checkForBreaksAndInsps(){

        //NOTE: If I do an action to reduce or increase the amount instead of
        //just doing it directly, I'd get a stack overflow if someone applies
        //more than 4 mood at once (e.g by stacking copies of Sanguine)
        while (amount >= 4)
        {
            getInspiration();
            amount -= 4;
            addToBot(new WaitAction(.25f));
        }

        while (amount <= -4)
        {
            getMentalBreak();
            amount += 4;
            addToBot(new WaitAction(.25f));
        }

        if (amount == 0)
            addToTop(new RemoveSpecificPowerAction(owner, owner, ID));

        if(amount < 0)
            type = PowerType.DEBUFF;
        else
            type = PowerType.BUFF;

        updateDescription();
    }

    public void getInspiration() {
        BreaksAndInspirations.inspiration(owner);
    }



    public void getMentalBreak(){
        BreaksAndInspirations.mentalBreak(owner);
    }

    @Override
    public void updateDescription() {
        if(amount > 0 )
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0]+ amount + DESCRIPTIONS[2];
    }


    public static class BreaksAndInspirations{

        public static void inspiration(AbstractCreature owner){
            int rand = AbstractDungeon.cardRandomRng.random(2);
            for(int i = 0; i < 20; ++i)
                AbstractDungeon.effectsQueue.add(new ShineSparkleEffect(owner.hb.x + (float)Math.random() * owner.hb.width, owner.hb.y + (float)Math.random() * owner.hb.height));
            Wiz.atb(new TalkAction(true, inspirationStrings.TEXT[rand], 2.5f, 2.5f));
            switch (rand)
            {
                case 1:
                    Wiz.applyToSelf(new BufferPower(owner, 1));
                    break;
                case 2:
                    Wiz.atb(new DrawCardAction(2));
                    Wiz.atb(new GainEnergyAction(2));
                    break;
                default:
                    fast(owner);
            }
        }

        private static void fast(AbstractCreature owner){

            int rand = new Random().nextInt(10);
            if(rand == 0)
                Wiz.atb(new SFXAction(modID + "fast"));

            Wiz.applyToSelf(new DupePower(owner, 1));

            if(rand == 0)
            {
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new WaitAction(.1f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
                Wiz.atb(new VFXAction(new WhirlwindEffect(), 0.5f));
            }
        }

        public static void mentalBreak(AbstractCreature owner)
        {
            int rand = AbstractDungeon.cardRandomRng.random(3) + 4;
            Wiz.atb(new VFXAction(owner, new VerticalAuraEffect(new Color(1, 0, 0, .5f), owner.hb.cX, owner.hb.cY), 0.25F));
            Wiz.atb(new TalkAction(true, inspirationStrings.TEXT[rand], 2.5f, 2.5f));
            if(owner instanceof AbstractPlayer)
            {
                switch (rand - 4)
                {
                    case 1:
                        Wiz.atb(new MakeTempCardInHandAction(new Slimed(), 2));
                        break;
                    case 2:
                        Wiz.atb(new MakeTempCardInDiscardAction(new Burn(), 2));
                        break;
                    case 3:
                        Wiz.atb(new MakeTempCardInHandAction(new Wound(), 2));
                        break;
                    default:
                        Wiz.atb(new MakeTempCardInDrawPileAction(new Dazed(), 2, true, false));
                }
            }
        }

    }
}
