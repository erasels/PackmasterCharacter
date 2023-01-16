package thePackmaster.vfx.rippack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.modID;

public class ArtAttackArtEffect {
    public static Texture RAINBOW = new Texture(modID + "Resources/images/vfx/rippack/rainbow.png");

    public static AbstractGameEffect RainbowBoomerang(AbstractMonster m) {
        AbstractPlayer p = AbstractDungeon.player;
        return new VfxBuilder(RAINBOW, p.hb.cX, p.hb.cY, 2.0f)
                .arc(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, Settings.HEIGHT * 0.6f)
                .emitEvery(
                        (x, y) -> new VfxBuilder(RAINBOW, x, y, 0.5f)
                                .fadeOutFromAlpha(0.5f, 0.4f)
                                .build(), 0.01f)
                .build();
    }

}