package thePackmaster.patches.clawpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.GashAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.clawpack.ClawStrengthPower;
import thePackmaster.util.Wiz;

@SpirePatch(
        clz = GashAction.class,
        method = "update"
)
public class ClawUsePatch {
        public static SpireReturn Postfix(GashAction __instance) {
            Wiz.applyToSelf(new ClawStrengthPower(Wiz.p(), __instance.amount));
            return SpireReturn.Return();
        }
    }
