package thePackmaster.patches.clawpack;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.clawpack.AbstractClawCard;
import thePackmaster.util.Wiz;

import java.util.Iterator;

@SpirePatch(
        clz = GashAction.class,
        method = "update"
)
public class ClawUsePatch {

    @SpirePrefixPatch
    public static void Prefix(GashAction __instance) {
        AbstractClawCard.ClawUp(__instance.amount, true);

    }
}
