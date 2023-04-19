package thePackmaster.patches.bardinspirepack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ReviveMonsterAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.unique.SummonGremlinAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import javassist.CtBehavior;
import thePackmaster.cards.bardinspirepack.ThickOfTheFight;
import thePackmaster.cards.colorlesspack.GolfBall;
import thePackmaster.patches.secretlevelpack.EnoughTalkPatch;
import thePackmaster.patches.secretlevelpack.SpecialCardGlowCheckPatch;

import java.util.ArrayList;

public class ThickOfTheFightPatch {
    private static void updateCards() {
        ArrayList<AbstractMonster> monsters;
        try {
            monsters = AbstractDungeon.getMonsters().monsters;
        } catch (NullPointerException ignore) {
            return;
        }

        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            return;
        }

        int monsterCount = 0;
        for (AbstractMonster m : monsters) {
            if (!m.isDeadOrEscaped()) {
                ++monsterCount;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ThickOfTheFight) {
                ((ThickOfTheFight) c).monstersChanged(monsterCount);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof ThickOfTheFight) {
                ((ThickOfTheFight) c).monstersChanged(monsterCount);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof ThickOfTheFight) {
                ((ThickOfTheFight) c).monstersChanged(monsterCount);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.limbo.group) {
            if (c instanceof ThickOfTheFight) {
                ((ThickOfTheFight) c).monstersChanged(monsterCount);
            }
        }
        if (AbstractDungeon.player.cardInUse instanceof ThickOfTheFight) {
            ((ThickOfTheFight) AbstractDungeon.player.cardInUse).monstersChanged(monsterCount);
        }
    }

    @SpirePatch(
            clz = SpawnMonsterAction.class,
            method = "update"
    )
    public static class MonsterSpawn {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(SpawnMonsterAction __instance) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "showHealthBar");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = ReviveMonsterAction.class,
            method = "update"
    )
    public static class MonsterRevive {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(ReviveMonsterAction __instance) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "healthBarRevivedEvent");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SummonGremlinAction.class,
            method = "update"
    )
    public static class SummonGremlin {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(SummonGremlinAction __instance) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "showHealthBar");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfCombatPreDrawLogic"
    )
    public static class BattleStart {
        public static void Prefix(AbstractPlayer __instance) {
            updateCards();
            EnoughTalkPatch.spokeLastTurn = false;
            SpecialCardGlowCheckPatch.playedGlowingCardThisTurn = false;
            GolfBall.BLOCK_AMT_LOST = 0;
        }
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "die",
            paramtypez = {boolean.class}
    )
    public static class MonsterDeath {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractMonster __instance, boolean triggerRelics) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = Darkling.class,
            method = "damage"
    )
    public static class DarklingHalfDeath {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(Darkling __instance, DamageInfo inf) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AwakenedOne.class,
            method = "damage"
    )
    public static class AwakenedOneHalfDeath {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AwakenedOne __instance, DamageInfo inf) {
            updateCards();
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    // Because Darklings and Awakened One don't revive normally
    @SpirePatch(
            clz = Darkling.class,
            method = "changeState"
    )
    @SpirePatch(
            clz = AwakenedOne.class,
            method = "changeState"
    )
    public static class Heal {
        public static void Postfix(AbstractMonster __instance, String key) {
            updateCards();
        }
    }
}
