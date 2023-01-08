package thePackmaster.actions.weaponspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.weaponspack.AbstractWeaponOrb;


public class UpgradeWeaponsAction extends AbstractGameAction {
    private int attackUpgrade;
    private int durabilityUpgrade;

    public UpgradeWeaponsAction(int attackUpgrade, int durabilityUpgrade) {
        this.attackUpgrade = attackUpgrade;
        this.durabilityUpgrade = durabilityUpgrade;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof AbstractWeaponOrb) {
                    AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) orb;
                    weaponOrb.upgrade(attackUpgrade, durabilityUpgrade);
                }
            }

            if (Settings.FAST_MODE) {
                this.isDone = true;
                return;
            }
        }

        this.tickDuration();
    }
}
