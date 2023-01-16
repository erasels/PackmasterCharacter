package thePackmaster.vfx.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makePath;

public class ElephantSplatEffect extends AbstractGameEffect {
    private static final float DURATION = 0.5F;

    private final float xCenter;
    private final float yCenter;

    public static final String ELEPHANT_HEAD_FILE = makePath("images/vfx/summonspack/elephant/ElephantHead.png");
    public static final String ELEPHANT_LEG_ONE_FILE = makePath("images/vfx/summonspack/elephant/ElephantLegOne.png");
    public static final String ELEPHANT_LEG_TWO_FILE = makePath("images/vfx/summonspack/elephant/ElephantLegTwo.png");
    public static final String ELEPHANT_LEG_THREE_FILE = makePath("images/vfx/summonspack/elephant/ElephantLegThree.png");
    public static final String ELEPHANT_LEG_FOUR_FILE = makePath("images/vfx/summonspack/elephant/ElephantLegFour.png");
    public static final String ELEPHANT_TAIL_FILE = makePath("images/vfx/summonspack/elephant/ElephantTail.png");
    public static final String ELEPHANT_RUMP_FILE = makePath("images/vfx/summonspack/elephant/ElephantRump.png");
    public static final String ELEPHANT_TRUNK_FILE = makePath("images/vfx/summonspack/elephant/ElephantTrunk.png");
    public static final String ELEPHANT_EAR_TOP_FILE = makePath("images/vfx/summonspack/elephant/ElephantEarTop.png");
    public static final String ELEPHANT_EAR_BOTTOM_FILE = makePath("images/vfx/summonspack/elephant/ElephantEarBottom.png");
    public static final String ELEPHANT_TOP_TORSO_FILE = makePath("images/vfx/summonspack/elephant/ElephantTopTorso.png");
    public static final String ELEPHANT_LEFT_TORSO_FILE = makePath("images/vfx/summonspack/elephant/ElephantBottomLeftTorso.png");
    public static final String ELEPHANT_RIGHT_TORSO_FILE = makePath("images/vfx/summonspack/elephant/ElephantBottomRightTorso.png");

    public ElephantSplatEffect(float x, float y) {
        xCenter = x;
        yCenter = y;
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        renderBehind = false;
    }

    public void update() {
        if (duration == startingDuration) {
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_HEAD_FILE, xCenter + 250*Settings.scale*Settings.scale, yCenter - 115*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_ONE_FILE, xCenter + 98*Settings.scale*Settings.scale, yCenter + 132*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_TWO_FILE, xCenter - 25*Settings.scale, yCenter + 160*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_THREE_FILE, xCenter - 170*Settings.scale, yCenter + 165*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_FOUR_FILE, xCenter - 270*Settings.scale, yCenter + 156*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TAIL_FILE, xCenter - 250*Settings.scale, yCenter - 5*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_RUMP_FILE, xCenter - 195*Settings.scale, yCenter - 163*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TRUNK_FILE, xCenter + 265*Settings.scale, yCenter + 97*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_EAR_TOP_FILE, xCenter + 87*Settings.scale, yCenter + -187*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_EAR_BOTTOM_FILE, xCenter + 113*Settings.scale, yCenter - 55*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TOP_TORSO_FILE, xCenter - 62*Settings.scale, yCenter - 162*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEFT_TORSO_FILE, xCenter - 140*Settings.scale, yCenter - 58*Settings.scale));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_RIGHT_TORSO_FILE, xCenter - 15*Settings.scale, yCenter - 5*Settings.scale));
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0)
            isDone = true;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    public void dispose() {
    }
}
