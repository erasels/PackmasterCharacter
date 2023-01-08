package thePackmaster.patches.weaponspack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import javassist.CtBehavior;
import thePackmaster.cards.weaponspack.SwordOfChaos;
import thePackmaster.cards.weaponspack.SwordOfFire;
import thePackmaster.cards.weaponspack.SwordOfWisdom;

import java.util.ArrayList;

@SpirePatch(clz = CardRewardScreen.class, method = "discoveryOpen", paramtypez = {})
public class SwordDiscoveryPatch {
    public static boolean swordDiscovery;
    public static boolean upgraded;

    @SpireInsertPatch(locator = Locator.class, localvars = {"derp"})
    public static void Insert(CardRewardScreen cardRewardScreen, ArrayList<AbstractCard> derp) {
        if (swordDiscovery) {
            derp.clear();
            AbstractCard swordOfFire = new SwordOfFire();
            AbstractCard swordOfWisdom = new SwordOfWisdom();
            AbstractCard swordOfChaos = new SwordOfChaos();
            if (upgraded) {
                swordOfFire.upgrade();
                swordOfWisdom.upgrade();
                swordOfChaos.upgrade();
            }
            derp.add(swordOfFire);
            derp.add(swordOfWisdom);
            derp.add(swordOfChaos);

            cardRewardScreen.rewardGroup = derp;
        }
        swordDiscovery = false;
        upgraded = false;
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardRewardScreen.class, "rewardGroup");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}