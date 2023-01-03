package thePackmaster.patches;

import basemod.patches.whatmod.WhatMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;

import java.util.Objects;
import java.util.Optional;
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
                Optional<AbstractCardPack> foundPack = SpireAnniversary5Mod.allPacks.stream()
                        .filter(pack -> pack.cards.stream().anyMatch(card -> card.getClass() == c))
                        .findFirst();
                foundPack.ifPresent(abstractCardPack -> name[0] = name[0] + " : " + abstractCardPack.name);
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
