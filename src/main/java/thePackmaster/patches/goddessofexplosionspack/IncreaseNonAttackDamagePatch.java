package thePackmaster.patches.goddessofexplosionspack;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import thePackmaster.powers.goddessofexplosionspack.ExplosiveFormPower;

@SpirePatch(clz = AbstractMonster.class, method = "damage")
public class IncreaseNonAttackDamagePatch {
    public static void Prefix(AbstractMonster m, DamageInfo info) {
        if (AbstractDungeon.player != null && (info.owner == null || info.owner == AbstractDungeon.player) && !m.hasPower(IntangiblePower.POWER_ID) && !m.hasPower(IntangiblePlayerPower.POWER_ID)) {
            if (info.type == DamageInfo.DamageType.THORNS) {
                if (AbstractDungeon.player.hasPower(ExplosiveFormPower.POWER_ID)) {
                    info.output += info.output*AbstractDungeon.player.getPower(ExplosiveFormPower.POWER_ID).amount;
                }
            }
        }
    }
}
