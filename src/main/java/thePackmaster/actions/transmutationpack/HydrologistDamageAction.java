package thePackmaster.actions.transmutationpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.vfx.transmutationpack.HydrologistParticle;
import thePackmaster.vfx.transmutationpack.HydrologistWaterbendingEffect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class HydrologistDamageAction extends AbstractGameAction {
    private static final HashMap<AbstractHydrologistCard.Subtype, List<String>> sfxMap = initializeSfxMap();
    private static final HashMap<AbstractHydrologistCard.Subtype, Color> colorMap = initializeColorMap();
    private static final float EFFECT_DURATION = 0.5f;
    private final DamageInfo info;
    private final AbstractHydrologistCard.Subtype tag;
    private final HydrologistWaterbendingEffect effect;
    private Vector2 startPosition = null;
    private Vector2 targetPosition = null;
    private Vector2 returnPosition = null;

    public HydrologistDamageAction(AbstractHydrologistCard.Subtype tag, AbstractCreature target, DamageInfo info) {
        setValues(target, info);
        this.info = info;
        this.tag = tag;
        effect = new HydrologistWaterbendingEffect(tag);
        duration = EFFECT_DURATION;
        if (target != null) {
            targetPosition = new Vector2(target.hb.cX, target.hb.cY);
        }
        startDuration = duration;
    }

    @Override
    public void update() {
        if (shouldCancelAction() && info.type != DamageInfo.DamageType.THORNS) {
            isDone = true;
            return;
        }
        if (startPosition == null) {
            AbstractDungeon.effectList.add(effect);
            startPosition = getRandomPositionNearAttacker();
            returnPosition = getRandomPositionNearAttacker();
            CardCrawlGame.sound.playV("ATTACK_WHIFF_1", 2.0f);
        }
        Vector2 interPosition = new Vector2();
        if (duration > startDuration / 2f) {
            //interpolate new vector2 coordinates to center of target
            interPosition.x = Interpolation.linear.apply(startPosition.x, targetPosition.x, 1F - ((duration - (startDuration / 2.0F)) / (startDuration / 2.0F)));
            interPosition.y = Interpolation.swingIn.apply(startPosition.y, targetPosition.y, 1F - ((duration - (startDuration / 2.0F)) / (startDuration / 2.0F)));
            effect.set(interPosition);
            duration -= Gdx.graphics.getDeltaTime();
            //if duration is <= startDuration/2, set result coordinates to startPosition
            if (duration <= startDuration / 2f) {
                doDamage();
                startPosition = interPosition.cpy();
            }
        } else {
            //interpolate new vector2 coordinates to waterbending position
            interPosition.x = Interpolation.linear.apply(startPosition.x, returnPosition.x, 1F - (duration / (startDuration / 2.0F)));
            interPosition.y = Interpolation.swingIn.apply(startPosition.y, returnPosition.y, 1F - (duration / (startDuration / 2.0F)));
            effect.set(interPosition);
            duration -= Gdx.graphics.getDeltaTime();
            if (duration <= 0) {
                isDone = true;
            }
        }
    }

    private Vector2 getRandomPositionNearAttacker() {
        Random random = new Random();
        float width = info.owner.hb_w;
        float height = info.owner.hb_h;
        return new Vector2(random.nextFloat() * width + info.owner.hb_x, random.nextFloat() * height + info.owner.hb_y);
    }

    private void doDamage() {
        target.tint.color.set(colorMap.get(tag).cpy());
        target.tint.changeColor(Color.WHITE.cpy());
        target.damage(info);
        generateParticle(tag, target);
        CardCrawlGame.sound.play(sfxMap.get(tag).get(MathUtils.random(sfxMap.get(tag).size() - 1)));
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }

    private static HashMap<AbstractHydrologistCard.Subtype, List<String>> initializeSfxMap() {
        HashMap<AbstractHydrologistCard.Subtype, List<String>> retVal = new HashMap<>();
        retVal.put(AbstractHydrologistCard.Subtype.ICE, Arrays.asList(SpireAnniversary5Mod.ICE_IMPACT_1_KEY, SpireAnniversary5Mod.ICE_IMPACT_2_KEY, SpireAnniversary5Mod.ICE_IMPACT_3_KEY));
        retVal.put(AbstractHydrologistCard.Subtype.WATER, Arrays.asList(SpireAnniversary5Mod.WATER_IMPACT_1_KEY, SpireAnniversary5Mod.WATER_IMPACT_2_KEY, SpireAnniversary5Mod.WATER_IMPACT_3_KEY));
        retVal.put(AbstractHydrologistCard.Subtype.STEAM, Arrays.asList(SpireAnniversary5Mod.STEAM_IMPACT_1_KEY, SpireAnniversary5Mod.STEAM_IMPACT_2_KEY, SpireAnniversary5Mod.STEAM_IMPACT_3_KEY));
        return retVal;
    }

    private static HashMap<AbstractHydrologistCard.Subtype, Color> initializeColorMap() {
        HashMap<AbstractHydrologistCard.Subtype, Color> retVal = new HashMap<>();
        retVal.put(AbstractHydrologistCard.Subtype.ICE, Color.CYAN);
        retVal.put(AbstractHydrologistCard.Subtype.WATER, Color.BLUE);
        retVal.put(AbstractHydrologistCard.Subtype.STEAM, Color.GRAY);
        return retVal;
    }

    private static void generateParticle(AbstractHydrologistCard.Subtype tag, AbstractCreature target) {
        float leftBound = target.hb.cX - (target.hb.width / 2);
        float rightBound = target.hb.cX + (target.hb.width / 2);
        float upBound = target.hb.cY + (target.hb.width / 2);
        float downBound = target.hb.cY - (target.hb.width / 2);
        float x = MathUtils.random(leftBound, rightBound);
        float y = MathUtils.random(downBound, upBound);
        float rotation = MathUtils.random(0.0f, 360.0f);
        float scale = MathUtils.random(1.1f, 1.4f);
        AbstractDungeon.effectList.add(new HydrologistParticle(tag, x, y, rotation, scale));
    }
}