package thePackmaster.patches.goddessofexplosionspack;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.powers.AbstractPower;

import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import thePackmaster.powers.goddessofexplosionspack.EveOfDestructionPower;
import thePackmaster.util.Wiz;


@SpirePatch(clz = AbstractMonster.class, method = "damage")
public class IncreaseNonAttackDamagePatch {
    public static void Prefix(AbstractMonster m, DamageInfo info) {
        if (AbstractDungeon.player != null && (info.owner == null || info.owner == AbstractDungeon.player) && !m.hasPower(IntangiblePower.POWER_ID) && !m.hasPower(IntangiblePlayerPower.POWER_ID)) {

            if (info.type != DamageInfo.DamageType.NORMAL) {
                AbstractPower imExplosive = Wiz.p().getPower(EveOfDestructionPower.POWER_ID);

                if (imExplosive != null) {
                    info.output += info.output*imExplosive.amount;

                }
            }
        }
    }
}
