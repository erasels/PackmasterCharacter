package thePackmaster.vfx.distortionpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ImproveEffect extends AbstractGameEffect {
    private static final Logger logger = LogManager.getLogger("Distortion");

    private static final ArrayList<Texture> tempTextures = new ArrayList<>();

    public static void _clean() {
        for (Texture t : tempTextures) {
            t.dispose();
        }

        tempTextures.clear();
    }


    public final AbstractMonster m;
    private final List<NotFinalPair<Texture, Consumer<Texture>>> textureSetters = new ArrayList<>();
    private int amount;

    private ImproveFollowup followup = null;

    public ImproveEffect(AbstractMonster m, int amount) {
        this.m = m;
        this.amount = amount;

        prep();
    }

    private void prep() {
        try {
            TextureAtlas _form = ReflectionHacks.getPrivate(this.m, AbstractCreature.class, "atlas");
            if (_form != null) {
                for (TextureRegion r : _form.getRegions()) {
                    textureSetters.add(new NotFinalPair<>(r.getTexture(), (t)->r.setTexture(_refactor(t, amount, true))));
                }
            } else {
                Texture img = ReflectionHacks.getPrivate(this.m, AbstractMonster.class, "img");
                if (img != null) {
                    textureSetters.add(new NotFinalPair<>(img,
                            (t)->ReflectionHacks.setPrivate(m, AbstractMonster.class, "img", _refactor(t, amount, tempTextures.contains(t)))));
                } else {
                    logger.error("Materia has no data: " + this.m.id);
                    this.isDone = true;
                }
            }
        } catch (Exception e) {
            logger.error("Failed to reconstruct m: " + this.m.id);
            e.printStackTrace();
        }
    }

    public void addFollowup(int amount) {
        if (this.followup == null) {
            this.followup = new ImproveFollowup(amount);
        }
        else {
            followup.addFollowup(amount);
        }
    }
    private static class ImproveFollowup {
        final int amount;
        ImproveFollowup followup;
        public ImproveFollowup(int amount) {
            this.amount = amount;
        }
        public void addFollowup(int amount) {
            if (followup == null) {
                followup = new ImproveFollowup(amount);
            }
            else {
                followup.addFollowup(amount);
            }
        }
    }

    @Override
    public void update() {
        if (textureSetters.isEmpty()) {
            if (followup == null) {
                isDone = true;
                return;
            }

            this.amount = followup.amount;
            this.followup = followup.followup;
            prep();

            if (textureSetters.isEmpty()) {
                isDone = true;
                return;
            }
        }

        NotFinalPair<Texture, Consumer<Texture>> tex = textureSetters.remove(textureSetters.size() - 1);
        tex.b.accept(tex.a);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }

    private static final float MAX_AREA = 10000;

    public static Texture _refactor(Texture t) {
        return _refactor(t, false);
    }

    public static Texture _refactor(Texture t, int alteration) {
        return _refactor(t, alteration, false);
    }

    public static Texture _refactor(Texture t, boolean dispose) {
        return _refactor(t, 10, dispose);
    }

    public static Texture _refactor(Texture t, int alteration, boolean dispose) {
        try {
            if (!t.getTextureData().isPrepared()) {
                t.getTextureData().prepare();
            }

            Pixmap re = t.getTextureData().consumePixmap();
            int altered;
            int alterations;
            int[][] area;
            int startX;
            int startY;
            int initX;
            int i;
            int initY;
            int b;
            if (MathUtils.randomBoolean()) {
                altered = 0;

                for (alterations = alteration + MathUtils.random(-2, 2); altered < alterations; ++altered) {
                    int width = MathUtils.random(t.getWidth() / 8, t.getWidth() / 5);
                    int height = MathUtils.random(t.getHeight() / 5, t.getHeight() / 3);
                    float scale = (width * height) / MAX_AREA;
                    if (scale > 1) {
                        width /= Math.sqrt(scale);
                        height /= Math.sqrt(scale);
                    }
                    area = new int[width][height];
                    startX = MathUtils.random(0, re.getWidth() - area.length);
                    startY = MathUtils.random(0, re.getHeight() - area[0].length);
                    initX = startX;

                    for(i = 0; i < area.length; i = ++initX - startX) {
                        initY = startY;

                        for(b = 0; b < area[0].length; b = ++initY - startY) {
                            area[i][b] = re.getPixel(initX, initY);
                        }
                    }

                    startY += MathUtils.random(t.getHeight() / 9, t.getHeight() / 5) * (MathUtils.randomBoolean() ? 1 : -1);
                    initX = startX;

                    for(i = 0; i < area.length; i = ++initX - startX) {
                        initY = startY;

                        for(b = 0; b < area[0].length; b = ++initY - startY) {
                            if (initY >= 0 && initY <= re.getHeight()) {
                                re.drawPixel(initX, initY, area[i][b]);
                            }
                        }
                    }
                }
            } else {
                altered = 0;

                for(alterations = MathUtils.random(11, 17); altered < alterations; ++altered) {
                    area = new int[MathUtils.random(t.getWidth() / 5, t.getWidth() / 3)][MathUtils.random(t.getHeight() / 8, t.getHeight() / 5)];
                    startX = MathUtils.random(0, re.getWidth() - area.length);
                    startY = MathUtils.random(0, re.getHeight() - area[0].length);
                    initX = startX;

                    for(i = 0; i < area.length; i = ++initX - startX) {
                        initY = startY;

                        for(b = 0; b < area[0].length; b = ++initY - startY) {
                            area[i][b] = re.getPixel(initX, initY);
                        }
                    }

                    startX += MathUtils.random(t.getWidth() / 9, t.getWidth() / 5) * (MathUtils.randomBoolean() ? 1 : -1);
                    initX = startX;

                    for(i = 0; i < area.length; i = ++initX - startX) {
                        initY = startY;

                        for(b = 0; b < area[0].length; b = ++initY - startY) {
                            if (initX >= 0 && initX <= re.getWidth()) {
                                re.drawPixel(initX, initY, area[i][b]);
                            }
                        }
                    }
                }
            }

            if (dispose) {
                t.dispose();
                tempTextures.remove(t);
            }

            return new Texture(re);
        } catch (Exception e) {
            return t;
        }
    }

    private static class NotFinalPair<A, B> {
        A a;
        B b;

        public NotFinalPair(A a, B b) {
            this.a = a;
            this.b = b;
        }
    }
}
