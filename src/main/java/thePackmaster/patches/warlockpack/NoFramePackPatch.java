//package thePackmaster.patches.warlockpack;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import thePackmaster.packs.WarlockPack;
//
//@SpirePatch(
//        clz = AbstractCard.class,
//        method = "renderSkillPortrait"
//)
//
//public class NoFramePackPatch {
//    public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
//        if (__instance.cardID.equals(WarlockPack.ID)) {
//            return SpireReturn.Return();
//        }
//        return SpireReturn.Continue();
//    }
//}
