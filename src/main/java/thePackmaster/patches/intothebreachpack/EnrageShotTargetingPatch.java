package thePackmaster.patches.intothebreachpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import javassist.CtBehavior;
import thePackmaster.cards.intothebreachpack.EnrageShot;

@SpirePatch(clz = AbstractPlayer.class, method = "updateSingleTargetInput")
// Patch to make Enrage Shot unable to target attacking enemies
// Credit to Darkglade for the original code that is adapted here
public class EnrageShotTargetingPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = {"hoveredCard", "hoveredMonster"})
    public static void MakeHoveredMonsterNull(AbstractPlayer instance, @ByRef AbstractCard[] hoveredCard, @ByRef AbstractMonster[] hoveredMonster) {
        if (hoveredCard[0] instanceof EnrageShot && hoveredMonster[0] != null && hoveredMonster[0].getIntentBaseDmg() >= 0)
            hoveredMonster[0] = null;
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(MonsterGroup.class, "areMonstersBasicallyDead");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}