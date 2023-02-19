package thePackmaster.patches.serpentinepack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.OnAttackStance;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "damage",
        paramtypez = {
                DamageInfo.class
        }
)
public class StanceOnAttackPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractMonster __instance, DamageInfo __info) {
            if (AbstractDungeon.player.stance instanceof OnAttackStance && __info.type.equals(DamageInfo.DamageType.NORMAL)) {
                OnAttackStance tmp = (OnAttackStance) AbstractDungeon.player.stance;
                tmp.onAttack(__info, __info.output, __instance);
            }
        }
    }