package thePackmaster.hats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.hats.specialhats.SpecialHat;
import thePackmaster.packs.AbstractCardPack;

import static thePackmaster.hats.HatMenu.specialHats;

public class HatsManager {
    public static String currentHat;

    public static void removeHat(AbstractPlayer player) {
        if (player instanceof ThePackmaster)
            ((ThePackmaster) player).resetHat();
    }

    public static void addHat(AbstractPlayer player, String hatID) {
        if (player instanceof ThePackmaster) {
            ((ThePackmaster) player).setUpHat(hatID);
        }
    }


    public static String getImagePathFromHatID(String hatID) {
        AbstractCardPack p = SpireAnniversary5Mod.packsByID.get(hatID);
        if (p != null) return p.getHatPath();
        return SpireAnniversary5Mod.modID + "Resources/images/hats/" + hatID.replace(SpireAnniversary5Mod.modID + ":", "") + "Hat.png";
    }

    public static void atRunStart() {
        if (currentHat != null) {
            if (HatMenu.invalidHatSelected) {
                SpireAnniversary5Mod.logger.info("an invalid hat was used. returning to default.");
                removeHat(AbstractDungeon.player);
                return;
            }
            addHat(AbstractDungeon.player, currentHat);
        } else {
            removeHat(AbstractDungeon.player);
        }
    }

    public static void preRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        SpecialHat shat = specialHats.get(currentHat);
        if (shat != null) {
            shat.preRenderPlayer(sb, p, x, y);
        }
    }

    public static void postRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y) {
        SpecialHat shat = specialHats.get(currentHat);
        if (shat != null) {
            shat.postRenderPlayer(sb, p, x, y);
        }
    }
}