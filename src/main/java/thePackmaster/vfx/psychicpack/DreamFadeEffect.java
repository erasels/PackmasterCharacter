package thePackmaster.vfx.psychicpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.audio.MusicMaster;
import com.megacrit.cardcrawl.audio.TempMusic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class DreamFadeEffect extends AbstractGameEffect {
    private static final float DUR = 1.0F;
    private final Color screenColor;
    private final float halfDuration;
    private final float ratio;
    private boolean playedJingle;
    private boolean usingTempMusic;

    public DreamFadeEffect(boolean sleeping) {
        this.screenColor = AbstractDungeon.fadeColor.cpy();

        this.duration = this.startingDuration = DUR;
        this.halfDuration = this.startingDuration / 2.0f;
        this.ratio = 1 / this.halfDuration;
        this.screenColor.a = 0.0F;

        playedJingle = !sleeping;

        ArrayList<TempMusic> tempMusic = ReflectionHacks.getPrivate(CardCrawlGame.music, MusicMaster.class, "tempTrack");

        usingTempMusic = !tempMusic.isEmpty();
        if (usingTempMusic)
        {
            for (TempMusic m : tempMusic)
                m.silenceInstantly();
        }
        else
        {
            CardCrawlGame.music.silenceBGMInstantly();
        }
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.updateBlackScreenColor();
        if (this.duration < this.halfDuration && !this.playedJingle) {
            this.playSleepJingle();
            playedJingle = true;
        }

        if (this.duration < this.startingDuration / 2.0F) {
            this.isDone = true;

            if (usingTempMusic)
            {
                ArrayList<TempMusic> tempMusic = ReflectionHacks.getPrivate(CardCrawlGame.music, MusicMaster.class, "tempTrack");

                for (TempMusic m : tempMusic)
                {
                    if (!m.isFadingOut)
                    {
                        m.isSilenced = false;
                        ReflectionHacks.setPrivate(m, TempMusic.class, "fadeTimer", 2.0f);
                        ReflectionHacks.setPrivate(m, TempMusic.class, "fadeTime", 2.0f);
                    }
                }
            }
            else
            {
                CardCrawlGame.music.unsilenceBGM();
            }

        }
    }

    private void playSleepJingle() {
        int roll = MathUtils.random(1, 3);
        String sleepSfx;

        switch(((AbstractDungeon.actNum - 1) % 3) + 1) {
            case 2:
                sleepSfx = "SLEEP_2-" + roll;
                break;
            case 3:
                sleepSfx = "SLEEP_3-" + roll;
                break;
            default:
                sleepSfx = "SLEEP_1-" + roll;
        }

        CardCrawlGame.sound.play(sleepSfx);
    }

    private void updateBlackScreenColor() {
        if (this.duration > this.halfDuration) {
            this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, 2 * (1 - (this.duration / this.startingDuration)));
        } else {
            this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * ratio);
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.screenColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, (float)Settings.WIDTH, (float)Settings.HEIGHT);
    }

    public void dispose() {
    }
}