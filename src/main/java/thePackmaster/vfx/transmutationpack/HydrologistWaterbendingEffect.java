package thePackmaster.vfx.transmutationpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.util.TriConsumer;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

public class HydrologistWaterbendingEffect extends AbstractGameEffect {
    //mask variables
    public static final float SPLINE_LENGTH = 1.0F; //in seconds
    public static final int LINE_WIDTH = (int)(20F * Settings.scale);

    //GDX variables
    private static FrameBuffer maskBuffer;
    private static FrameBuffer tileBuffer;
    private static ShapeRenderer shapeRenderer;

    //water variables
    private static final Texture WATER_TILE_SHEET = new Texture(SpireAnniversary5Mod.makePath("images/vfx/transmutationpack/WaterTileSheet.png"));
    private static TextureRegion[] waterTiles;
    private static final int WATER_ANIMATION_HORIZONTAL_FRAMES = 3;
    private static final int WATER_ANIMATION_VERTICAL_FRAMES = 7;
    private static final float WATER_ANIMATION_DURATION = 1.0f;
    private static int waterTileCount;
    private int currentWaterTile;

    //ice variables
    private static final Texture ICE_TILE = new Texture(SpireAnniversary5Mod.makePath("images/vfx/transmutationpack/IceTile.png"));
    private static TextureRegion iceTile;

    //steam variables
    private static final Texture STEAM_TILE = new Texture(SpireAnniversary5Mod.makePath("images/vfx/transmutationpack/SteamTile.png"));
    private static TextureRegion steamTile;
    private static final float STEAM_SCROLL_DURATION = 8.0F;

    //list of tiles to render and how to render them
    private static ArrayList<RenderInstructions> renderInstructions;

    //effect control
    private final ArrayList<Coordinates> spline;
    private final HashMap<Coordinates, Float> times;
    private float timer;
    private AbstractHydrologistCard.Subtype type;
    private boolean controlled = false;

    //add to this to define tile modes
    private static HashMap<AbstractHydrologistCard.Subtype, BehaviourPackage> effectsMap;

    public HydrologistWaterbendingEffect(AbstractHydrologistCard.Subtype type) {
        if (maskBuffer == null) {
            initialize();
        }

        spline = new ArrayList<>();
        times = new HashMap<>();
        setType(type);
    }

    private void setType(AbstractHydrologistCard.Subtype type) {
        this.type = type;
        switch (type) {
            case ICE:
                break;
            case STEAM:
                timer = STEAM_SCROLL_DURATION;
                break;
            case WATER:
                currentWaterTile = waterTileCount;
                timer = WATER_ANIMATION_DURATION;
                break;
        }
    }

    private static void initialize() {
        maskBuffer = createBuffer();
        tileBuffer = createBuffer();
        shapeRenderer = new ShapeRenderer();

        //texture initialization
        waterTileCount = WATER_ANIMATION_HORIZONTAL_FRAMES * WATER_ANIMATION_VERTICAL_FRAMES;
        waterTiles = new TextureRegion[waterTileCount];
        int i = 0;
        for (int w = 0; w < WATER_ANIMATION_HORIZONTAL_FRAMES; ++w) {
            for (int h = 0; h < WATER_ANIMATION_VERTICAL_FRAMES; ++h) {
                waterTiles[i] = new TextureRegion(WATER_TILE_SHEET,
                        w * (WATER_TILE_SHEET.getWidth() / WATER_ANIMATION_HORIZONTAL_FRAMES),
                        h * (WATER_TILE_SHEET.getHeight() / WATER_ANIMATION_VERTICAL_FRAMES),
                        (WATER_TILE_SHEET.getWidth() / WATER_ANIMATION_HORIZONTAL_FRAMES),
                        (WATER_TILE_SHEET.getHeight() / WATER_ANIMATION_VERTICAL_FRAMES));
                ++i;
            }
        }
        iceTile = new TextureRegion(ICE_TILE);
        steamTile = new TextureRegion(STEAM_TILE);

        //list initialization
        renderInstructions = new ArrayList<>();
        effectsMap = new HashMap<>();

        //water effect declaration
        TriConsumer<Float, Integer, HydrologistWaterbendingEffect> waterInstructor = (timer, currentWaterTile, effect) -> {
            renderInstructions.clear();
            renderInstructions.add(new RenderInstructions(waterTiles[currentWaterTile]));
            effect.findGridPoints();
        };
        Function<Float, Float> waterUpdater = timer -> {
            timer += Gdx.graphics.getDeltaTime();
            if (timer >= WATER_ANIMATION_DURATION) {
                timer = 0.0F;
            }
            return timer;
        };
        effectsMap.put(AbstractHydrologistCard.Subtype.WATER, new BehaviourPackage(waterUpdater, waterInstructor));

        //ice effect declaration
        TriConsumer<Float, Integer, HydrologistWaterbendingEffect> iceInstructor = (timer, currentWaterTile, effect) -> {
            renderInstructions.clear();
            renderInstructions.add(new RenderInstructions(iceTile));
            effect.findGridPoints();
        };
        Function<Float, Float> iceUpdater = timer -> timer;
        effectsMap.put(AbstractHydrologistCard.Subtype.ICE, new BehaviourPackage(iceUpdater, iceInstructor));

        //steam effect declaration
        TriConsumer<Float, Integer, HydrologistWaterbendingEffect> steamInstructor = (timer, currentWaterTile, effect) -> {
            renderInstructions.clear();
            renderInstructions.add(new RenderInstructions(steamTile, new Vector2(steamTile.getRegionWidth() * Settings.scale * (timer / STEAM_SCROLL_DURATION), 0), Color.WHITE.cpy(), false, false, 1.0f, 1.0f));
            renderInstructions.add(new RenderInstructions(steamTile, new Vector2(steamTile.getRegionWidth() * Settings.scale * (timer / STEAM_SCROLL_DURATION), 0), Color.WHITE.cpy(), false, true, 1.0f, 1.0f));
            effect.findGridPoints();
        };
        Function<Float, Float> steamUpdater = timer -> {
            timer += Gdx.graphics.getDeltaTime();
            if (timer >= STEAM_SCROLL_DURATION) {
                timer = 0.0F;
            }
            return timer;
        };
        effectsMap.put(AbstractHydrologistCard.Subtype.STEAM, new BehaviourPackage(steamUpdater, steamInstructor));
    }

    public void set(Vector2 coords) {
        Coordinates point = new Coordinates(coords.x, coords.y);
        recordPoint(point);
        controlled = true;
    }

    @Override
    public void update() {
        if (!controlled) {
            Coordinates last = spline.get(spline.size() - 1).cpy();
            recordPoint(last);
        } else {
            controlled = false;
        }
        float length = 0.0f;
        for (float time : times.values()) {
            length += time;
        }
        while (length > SPLINE_LENGTH) {
            Coordinates dot = spline.get(0);
            length -= times.get(dot);
            times.remove(dot);
            spline.remove(dot);
        }
        timer = effectsMap.get(type).updater.apply(timer);
        currentWaterTile = (int)Math.floor((timer / WATER_ANIMATION_DURATION) * waterTileCount);
        if (spline.size() > 2) {
            Coordinates last = spline.get(spline.size() - 1);
            boolean allSame = true;
            for (Coordinates vector : spline) {
                if (!vector.isSame(last)) {
                    allSame = false;
                    break;
                }
            }
            if (allSame) {
                isDone = true;
            }
        }
    }

    private void recordPoint(Coordinates point) {
        spline.add(point);
        times.put(point, Gdx.graphics.getDeltaTime());
    }

    @Override
    public void render(SpriteBatch sb) {
        //create the mask
        sb.end();
        TextureRegion mask = createMask();

        //create the tiles
        beginBuffer(tileBuffer);
        sb.begin();
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        effectsMap.get(type).instructor.accept(timer, currentWaterTile, this);
        renderTiles(sb);

        //mask the tiles
        sb.setBlendFunction(0, GL20.GL_SRC_ALPHA);
        sb.setColor(Color.WHITE.cpy());
        sb.draw(mask, 0, 0);

        //collect the final texture
        sb.end();
        tileBuffer.end();
        TextureRegion texture = getBufferTexture(tileBuffer);
        sb.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //render the effect on the main camera
        sb.begin();
        sb.draw(texture, 0,0);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void dispose() {

    }

    private TextureRegion createMask() {
        beginBuffer(maskBuffer);
        Color color = Color.BLACK.cpy();
        color.a = 0.5f;
        renderCurve(color, LINE_WIDTH); //half transparent larger outline for anti-aliasing
        color.a = 1.0f;
        renderCurve(color, LINE_WIDTH - 1);
        maskBuffer.end();
        return getBufferTexture(maskBuffer);
    }

    private void renderCurve(Color color, int width) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        int lineWidth;
        for (int i = 0; i < spline.size() - 1; ++i) {
            if (i < width) {
                lineWidth = i + 1;
            } else if (i > spline.size() - width) {
                lineWidth = spline.size() - i;
            } else {
                lineWidth = width;
            }
            Vector2 start = spline.get(i).toVector();
            Vector2 mid = spline.get(i+1).toVector();
            shapeRenderer.rectLine(start, mid, lineWidth);
            if ((i < spline.size() - 3)) {
                Vector2 end = spline.get(i + 2).toVector();
                shapeRenderer.rectLine(start, end, lineWidth);
            }
        }
        shapeRenderer.end();
    }

    private void findGridPoints() {
        for (RenderInstructions instructions : renderInstructions) {
            Coordinates bottomLeft = spline.get(0).cpy();
            Coordinates topRight = spline.get(0).cpy();
            for (Coordinates point : spline) {
                if (point.x < bottomLeft.x) {
                    bottomLeft.x = point.x;
                }
                if (point.y < bottomLeft.y) {
                    bottomLeft.y = point.y;
                }
                if (point.x > topRight.x) {
                    topRight.x = point.x;
                }
                if (point.y > topRight.y) {
                    topRight.y = point.y;
                }
            }
            bottomLeft.add(-LINE_WIDTH, -LINE_WIDTH);
            topRight.add(LINE_WIDTH, LINE_WIDTH);
            float scaleWidth = instructions.texture.getRegionWidth() * Settings.scale * instructions.scaleX;
            float scaleHeight = instructions.texture.getRegionHeight() * Settings.scale * instructions.scaleY;
            float gridBottom = instructions.offSet.y;
            float gridTop = scaleHeight + instructions.offSet.y;
            if (gridBottom > bottomLeft.y) {
                gridBottom -= scaleHeight;
                gridTop -= scaleHeight;
            }
            float gridLeft = instructions.offSet.x;
            float gridRight = scaleWidth + instructions.offSet.x;
            if (gridLeft > bottomLeft.x) {
                gridLeft -= scaleWidth;
                gridRight -= scaleWidth;
            }
            int verticalTiles = 1;
            int horizontalTiles = 1;
            while (gridTop < topRight.y) {
                gridTop += scaleHeight;
                verticalTiles++;
            }
            while (gridRight < topRight.x) {
                gridRight += scaleWidth;
                horizontalTiles++;
            }
            while (gridBottom + scaleHeight < bottomLeft.y) {
                gridBottom += scaleHeight;
                verticalTiles--;
            }
            while (gridLeft + scaleWidth < bottomLeft.x) {
                gridLeft += scaleWidth;
                horizontalTiles--;
            }
            Vector2 gridBottomLeft = new Vector2(gridLeft, gridBottom);
            instructions.setInfo(gridBottomLeft, horizontalTiles, verticalTiles);
        }
    }

    private void renderTiles(SpriteBatch sb) {
        for (RenderInstructions instruction : renderInstructions) {
            sb.setColor(instruction.color);
            instruction.texture.flip(instruction.flipHorizontal, instruction.flipVertical);
            for (int x = 0; x < instruction.horizontalTiles; ++x) {
                for (int y = 0; y < instruction.verticalTiles; ++y) {
                    sb.draw(instruction.texture,
                            instruction.origin.x + (x * instruction.texture.getRegionWidth() * Settings.scale * instruction.scaleX),
                            instruction.origin.y + (y * instruction.texture.getRegionHeight() * Settings.scale * instruction.scaleY),
                            0, 0,
                            instruction.texture.getRegionWidth(),
                            instruction.texture.getRegionHeight(),
                            Settings.scale * instruction.scaleX,
                            Settings.scale * instruction.scaleY,
                            0);
                }
            }
            instruction.texture.flip(instruction.flipHorizontal, instruction.flipVertical);
        }
    }

    private static class BehaviourPackage {
        public Function<Float, Float> updater;
        public TriConsumer<Float, Integer, HydrologistWaterbendingEffect> instructor;

        public BehaviourPackage(Function<Float, Float> updater, TriConsumer<Float, Integer, HydrologistWaterbendingEffect> instructor) {
            this.updater = updater;
            this.instructor = instructor;
        }
    }

    private static class RenderInstructions {
        public TextureRegion texture;
        public Vector2 offSet;
        public boolean flipHorizontal;
        public boolean flipVertical;
        public float scaleX;
        public float scaleY;
        public Color color;
        public int horizontalTiles;
        public int verticalTiles;
        public Vector2 origin;

        public RenderInstructions(TextureRegion texture, Vector2 offSet, Color color, boolean flipHorizontal, boolean flipVertical, float scaleX, float scaleY) {
            this.texture = texture;
            this.offSet = offSet;
            this.flipHorizontal = flipHorizontal;
            this.flipVertical = flipVertical;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            if (this.scaleX == 0.0f) {
                System.out.println("ERROR: invalid waterbending X scale. Scale cannot be 0");
                this.scaleX = 1;
            }
            if (this.scaleY == 0.0F) {
                System.out.println("ERROR: invalid waterbending Y scale. Scale cannot be 0");
                this.scaleY = 1;
            }
            this.color = color;
        }

        public RenderInstructions(TextureRegion texture) {
            this(texture, new Vector2(0, 0), Color.WHITE.cpy(), false, false, 1, 1);
        }

        public void setInfo(Vector2 origin, int horizontalTiles, int verticalTiles) {
            this.origin = origin;
            this.horizontalTiles = horizontalTiles;
            this.verticalTiles = verticalTiles;
        }
    }

    public static FrameBuffer createBuffer() {
        return new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
    }

    public static void beginBuffer(FrameBuffer fbo) {
        fbo.begin();
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        Gdx.gl.glColorMask(true,true,true,true);
    }

    public static TextureRegion getBufferTexture(FrameBuffer fbo) {
        TextureRegion texture = new TextureRegion(fbo.getColorBufferTexture());
        texture.flip(false, true);
        return texture;
    }

    //eat shit Vector2 and it's override of .equals
    public static class Coordinates {
        public float x;
        public float y;

        public Coordinates(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public Coordinates cpy() {
            return new Coordinates(x, y);
        }

        public Vector2 toVector() {
            return new Vector2(x, y);
        }

        public void add(int x, int y) {
            this.x += x;
            this.y += y;
        }

        public boolean isSame(Coordinates other) {
            return x == other.x && y == other.y;
        }
    }
}
