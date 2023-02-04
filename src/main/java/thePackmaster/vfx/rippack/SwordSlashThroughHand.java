package thePackmaster.vfx.rippack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.modID;
import static thePackmaster.util.Wiz.isArtCard;

public class SwordSlashThroughHand {

    public static Texture SWORD = new Texture(modID + "Resources/images/vfx/rippack/sword.png");

    public static AbstractGameEffect Swing() {
        AbstractPlayer p = AbstractDungeon.player;
        int artCardsInExhaust = AbstractDungeon.player.exhaustPile.group.stream().filter(card -> isArtCard(card)).collect(Collectors.toList()).size();
        float fadeout = artCardsInExhaust > 0 ? 0.0f : 0.5f;
        float duration = artCardsInExhaust > 0 ? 0.75f : 1.0f;
        return new VfxBuilder(SWORD, p.hb.cX + 100.0f * Settings.scale, p.hb.cY + 100.0f * Settings.scale, duration)
                .setScale(0.6f)
                .fadeOut(fadeout)
                .build();
    }
}
