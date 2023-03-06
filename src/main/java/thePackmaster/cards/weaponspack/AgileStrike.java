package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.weaponspack.AbstractWeaponOrb;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AgileStrike extends AbstractWeaponsPackCard {

    public static final String ID = makeID("AgileStrike");
    private static final int COST = 1;
    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 4;

    public AgileStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = ATTACK_DMG;

        this.tags.add(CardTags.STRIKE);

        if (Wiz.isInCombat()) {
            reduceCostToZeroForATurnIfNeeded();
        }
    }

    /* Cost Refreshing Logic is also in EquipAction. */

    @Override
    public void applyPowers() {
        reduceCostToZeroForATurnIfNeeded();
        super.applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    private void reduceCostToZeroForATurnIfNeeded() {
        if (costForTurn == 0) {
            return;
        }
        boolean shouldCostBeReduced = false;

        for (AbstractOrb orb : AbstractDungeon.actionManager.orbsChanneledThisTurn) {
            if (orb instanceof AbstractWeaponOrb) {
                shouldCostBeReduced = true;
                break;
            }
        }

        if (shouldCostBeReduced) {
            setCostForTurn(0);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.actionManager.orbsChanneledThisTurn.stream().anyMatch(q -> q instanceof AbstractWeaponOrb) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        }
    }

}
