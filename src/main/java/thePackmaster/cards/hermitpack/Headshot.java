package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.hermitpack.EnumPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Headshot extends AbstractHermitCard {
    public final static String ID = makeID("Headshot");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;

    public Headshot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int dam = this.damage;

        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, dam, damageTypeForTurn), EnumPatch.HERMIT_GUN));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isDeadOnPos()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

        if (isDeadOnPos()) {
            int base_dam = this.damage;
            this.damage += base_dam;
        }

        isDamageModified = damage != baseDamage;
    }

    public boolean isDeadOnPos()
    {
        double hand_pos = (AbstractDungeon.player.hand.group.indexOf(this)+0.5);
        double hand_size = (AbstractDungeon.player.hand.size());
        double relative = Math.abs(hand_pos-hand_size/2);

        return (relative<1);
    }

    //Upgraded stats.
    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}