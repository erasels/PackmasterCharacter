package thePackmaster.powers.weaponspack;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.weaponspack.AbstractWeaponOrb;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WeaponPower extends AbstractPackmasterPower implements InvisiblePower {

    public static final String POWER_ID = makeID("WeaponPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;

    public WeaponPower(AbstractCreature owner) {
        super(POWER_ID, NAME, NeutralPowertypePatch.NEUTRAL, false, owner, -1);
        this.loadRegion("painfulStabs");
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card.type.equals(CardType.ATTACK)) {
            for (AbstractWeaponOrb weaponOrb : getWeaponsToUse()) {
                if (!weaponOrb.isJustAddedUsingAttackCard()) {
                    weaponOrb.use(true);
                }
            }
            clearJustAddedUsingAttackCardFlagInWeapons();
        }
        refreshWeapons();
    }

    public void refreshWeapons() {
        destroyWeaponsWithNoDurability();
    }

    private void destroyWeaponsWithNoDurability() {
        List<AbstractOrb> weaponsToRemove = new ArrayList<>();

        boolean hasAtLeastOneWeaponLeft = false;

        List<AbstractOrb> orbsList = AbstractDungeon.player.orbs;
        for (int i = 0; i < orbsList.size(); i++) {
            AbstractOrb orb = orbsList.get(i);
            if (orb instanceof AbstractWeaponOrb) {
                AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) orb;
                if (weaponOrb.getDurability() <= 0) {
                    weaponsToRemove.add(orb);
                } else {
                    hasAtLeastOneWeaponLeft = true;
                }
            }
        }

        for (int i = 0; i < weaponsToRemove.size(); i++) {
            AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) weaponsToRemove.get(i);
            weaponOrb.onEvoke();
        }
        orbsList.removeAll(weaponsToRemove);

        AbstractDungeon.player.maxOrbs -= weaponsToRemove.size();
        if (AbstractDungeon.player.maxOrbs < 0) {
            AbstractDungeon.player.maxOrbs = 0;
        }

        for (int i = 0; i < orbsList.size(); ++i) {
            orbsList.get(i).setSlot(i, AbstractDungeon.player.maxOrbs);
        }

        if (!hasAtLeastOneWeaponLeft) {
            removeThisInvisibly();
        }
    }

    private void clearJustAddedUsingAttackCardFlagInWeapons() {
        List<AbstractOrb> orbsList = AbstractDungeon.player.orbs;
        for (int i = 0; i < orbsList.size(); i++) {
            AbstractOrb orb = orbsList.get(i);
            if (orb instanceof AbstractWeaponOrb) {
                AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) orb;
                weaponOrb.clearJustAddedUsingAttackCard();
            }
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        super.atStartOfTurnPostDraw();
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof AbstractWeaponOrb) {
                AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) o;
                weaponOrb.effectAtStartOfTurnPostDraw();
            }
        }
    }

    @Override
    public float atDamageGive(float damage, DamageType type) {
        if (type.equals(DamageType.NORMAL)) {
            int weaponAttack = 0;
            for (AbstractWeaponOrb weaponOrb : getWeaponsToUse()) {
                weaponAttack += weaponOrb.getDamageToDeal();
            }
            return super.atDamageGive(damage, type) + weaponAttack;
        } else {
            return super.atDamageGive(damage, type);
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        if (target != this.owner && info.type == DamageType.NORMAL) {
            for (AbstractWeaponOrb weaponOrb : getWeaponsToUse()) {
                weaponOrb.effectOnAttack(info, damageAmount, target);
            }
        }
    }

    private List<AbstractWeaponOrb> getWeaponsToUse() {
        List<AbstractWeaponOrb> weaponsToUseResult = new ArrayList<>();
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof AbstractWeaponOrb) {
                AbstractWeaponOrb weaponOrb = (AbstractWeaponOrb) o;
                weaponsToUseResult.add(weaponOrb);
                break;
            }
        }
        return weaponsToUseResult;
    }
}
