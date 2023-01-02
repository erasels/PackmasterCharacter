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

    public static final String ELEPHANT_HEAD_FILE = makePath("images/vfx/Elephant/ElephantHead.png");
    public static final String ELEPHANT_LEG_ONE_FILE = makePath("images/vfx/Elephant/ElephantLegOne.png");
    public static final String ELEPHANT_LEG_TWO_FILE = makePath("images/vfx/Elephant/ElephantLegTwo.png");
    public static final String ELEPHANT_LEG_THREE_FILE = makePath("images/vfx/Elephant/ElephantLegThree.png");
    public static final String ELEPHANT_LEG_FOUR_FILE = makePath("images/vfx/Elephant/ElephantLegFour.png");
    public static final String ELEPHANT_TAIL_FILE = makePath("images/vfx/Elephant/ElephantTail.png");
    public static final String ELEPHANT_RUMP_FILE = makePath("images/vfx/Elephant/ElephantRump.png");
    public static final String ELEPHANT_TRUNK_FILE = makePath("images/vfx/Elephant/ElephantTrunk.png");
    public static final String ELEPHANT_EAR_TOP_FILE = makePath("images/vfx/Elephant/ElephantEarTop.png");
    public static final String ELEPHANT_EAR_BOTTOM_FILE = makePath("images/vfx/Elephant/ElephantEarBottom.png");
    public static final String ELEPHANT_TOP_TORSO_FILE = makePath("images/vfx/Elephant/ElephantTopTorso.png");
    public static final String ELEPHANT_LEFT_TORSO_FILE = makePath("images/vfx/Elephant/ElephantBottomLeftTorso.png");
    public static final String ELEPHANT_RIGHT_TORSO_FILE = makePath("images/vfx/Elephant/ElephantBottomRightTorso.png");

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
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_HEAD_FILE, xCenter + 250, yCenter - 115));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_ONE_FILE, xCenter + 98, yCenter + 132));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_TWO_FILE, xCenter - 25, yCenter + 160));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_THREE_FILE, xCenter - 170, yCenter + 165));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEG_FOUR_FILE, xCenter - 270, yCenter + 156));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TAIL_FILE, xCenter - 250, yCenter - 5));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_RUMP_FILE, xCenter - 195, yCenter - 163));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TRUNK_FILE, xCenter + 265, yCenter + 97));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_EAR_TOP_FILE, xCenter + 87, yCenter + -187));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_EAR_BOTTOM_FILE, xCenter + 113, yCenter - 55));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_TOP_TORSO_FILE, xCenter - 62, yCenter - 162));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_LEFT_TORSO_FILE, xCenter - 140, yCenter - 58));
            AbstractDungeon.effectsQueue.add(new ElephantPieceEffect(ELEPHANT_RIGHT_TORSO_FILE, xCenter - 15, yCenter - 5));
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
