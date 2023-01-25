package thePackmaster.patches.coresetpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.buttons.EndTurnButton;
import thePackmaster.powers.coresetpack.MayhemFormPower;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;

@SpirePatch(clz = EndTurnButton.class, method = "render")
public class EndTurnButtonChanges {
    private static Texture reminderTex = TexLoader.getTexture("anniv5Resources/images/powers/MayhemFormPower84.png");
    private static final float SHOW_X = 1640.0F * Settings.xScale;
    private static final float SHOW_Y = 210.0F * Settings.yScale;

    public static void Postfix(EndTurnButton __instance, SpriteBatch sb) {
        boolean isHidden = ReflectionHacks.getPrivate(__instance, EndTurnButton.class, "isHidden");
        if (!isHidden && AbstractDungeon.overlayMenu.endTurnButton.enabled) {
            AbstractPower form = AbstractDungeon.player.getPower(MayhemFormPower.POWER_ID);
            if (form != null) {
                if (!((MayhemFormPower) form).activatedThisTurn) {
                    Color org = sb.getColor();
                    sb.setColor(Color.WHITE);
                    ImageHelper.drawTextureScaled(sb, reminderTex, SHOW_X, SHOW_Y);
                    sb.setColor(org);
                }
            }
        }
    }
}
