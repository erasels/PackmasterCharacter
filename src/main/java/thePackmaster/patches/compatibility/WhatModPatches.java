package thePackmaster.patches.compatibility;

import basemod.patches.whatmod.WhatMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;

import java.util.Objects;
import java.util.Set;

public class WhatModPatches
{
    @SpirePatch2(
            clz = WhatMod.class,
            method = "getBody"
    )
    public static class Cards
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"c", "name"}
        )
        public static void AddPackName(Class<?> c, @ByRef String[] name)
        {
            if (Objects.equals(SpireAnniversary5Mod.modID, WhatMod.findModID(c))) {
                String packID = SpireAnniversary5Mod.cardClassParentMap.get(c);
                AbstractCardPack pack = SpireAnniversary5Mod.packsByID.get(packID);
                if (pack != null) {
                    name[0] = name[0] + ": " + pack.name;
                }
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Set.class, "add");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
