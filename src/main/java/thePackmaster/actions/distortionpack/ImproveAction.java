package thePackmaster.actions.distortionpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.patches.BetterPowerNegationCheckPatch;

import java.util.ArrayList;

public class ImproveAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger("Distortion");
    
    private static final ArrayList<Texture> tempTextures = new ArrayList<>();

    private ApplyPowerAction distortionApplier = null;


    public static void _clean() {
        for (Texture t : tempTextures) {
            t.dispose();
        }

        tempTextures.clear();
    }


    private AbstractMonster materia;

    public ImproveAction(AbstractMonster m, ApplyPowerAction distortionApplier) {
        this(m);
        this.distortionApplier = distortionApplier;
    }

    public ImproveAction(AbstractMonster target) {
        this.materia = target;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (distortionApplier != null && !BetterPowerNegationCheckPatch.Field.appliedSuccess.get(distortionApplier)) {
            this.isDone = true;
            return;
        }

        try {
            TextureAtlas _form = ReflectionHacks.getPrivate(this.materia, AbstractCreature.class, "atlas");
            if (_form != null) {
                ArrayList<Texture> regionTextures = new ArrayList<>();
                _form.getRegions();

                for (TextureRegion r : _form.getRegions()) {
                    regionTextures.add(r.getTexture());
                }

                for(int i = 0; i < regionTextures.size(); ++i) {
                    _form.getTextures().remove(regionTextures.get(i));
                    Texture _reconstructed = _refactor(regionTextures.get(i), true);
                    regionTextures.set(i, _reconstructed);
                    _form.getTextures().add(_reconstructed);
                }

                for (TextureRegion r : _form.getRegions()) {
                    r.setTexture(regionTextures.remove(0));
                }

            } else {
                Texture img = ReflectionHacks.getPrivate(this.materia, AbstractMonster.class, "img");
                if (img != null) {
                    img = _refactor(img, false);
                    tempTextures.add(img);
                    ReflectionHacks.setPrivate(this.materia, AbstractMonster.class, "img", img);
                } else {
                    logger.error("Materia has no data: " + this.materia.id);
                }
            }
        } catch (Exception e) {
            logger.error("Failed to reconstruct materia: " + this.materia.id);
            e.printStackTrace();
        }

        this.isDone = true;
    }

    public static Texture _refactor(Texture t) {
        return _refactor(t, false);
    }

    public static Texture _refactor(Texture t, boolean dispose) {
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

                for(alterations = MathUtils.random(11, 17); altered < alterations; ++altered) {
                    area = new int[MathUtils.random(t.getWidth() / 8, t.getWidth() / 5)][MathUtils.random(t.getHeight() / 5, t.getHeight() / 3)];
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
            }

            return new Texture(re);
        } catch (Exception var12) {
            return t;
        }
    }
}