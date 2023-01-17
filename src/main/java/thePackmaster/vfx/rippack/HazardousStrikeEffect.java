package thePackmaster.vfx.rippack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class HazardousStrikeEffect {

    public static Texture SWORD = new Texture(modID + "Resources/images/vfx/rippack/sword.png");
    public static float scale = 0.6f;

    public static AbstractGameEffect CutCardsInHand() {
        return new VfxBuilder(SWORD, -20.0f * Settings.scale, 50.0f * Settings.scale, 0.75f)
                .setAngle(270.0f)
                .moveX(-20.0f * Settings.scale, Settings.WIDTH + 50.0f * Settings.scale)
                .build();
    }


    public static AbstractGameEffect JumpSlash(AbstractCreature c) {
        AbstractPlayer p = AbstractDungeon.player;
        Color color = new Color(1.0f, 1.0f, 120.0f / 255.0f, 1.0f);
        float startRaiseX = c.hb.cX - 50.0f;
        float startRaiseY = c.hb.cY + 50.0f;

        return new VfxBuilder(SWORD, p.hb.cX + 20.0f * Settings.scale, p.hb.cY, 0.4f)
                .setAngle(-65.0f)
                .setColor(color)
                .setScale(scale)
                .moveX(p.hb.cX, c.hb.cX)
                .andThen(0.3f)
                .setScale(scale)
                .rotateTo(-65.0f, 25.0f, VfxBuilder.Interpolations.SMOOTH)
                .playSoundAt(0.01f, makeID("RipPack_Sword"))
                .moveX(startRaiseX, startRaiseX - 200.0f)
                .moveY(startRaiseY, startRaiseY + 200.0f)
                .andThen(0.2f)
                .setScale(scale)
                .andThen(0.25f)
                .setScale(scale)
                .rotateTo(25.0f, -160.0f, VfxBuilder.Interpolations.SMOOTH)
                .moveX(startRaiseX - 200.0f, startRaiseX + 150.0f)
                .moveY(startRaiseY + 200.0f, startRaiseY - 300.0f)
                .andThen(0.1f)
                .build();
    }
}
