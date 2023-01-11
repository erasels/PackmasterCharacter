package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Headshot extends AbstractHermitCard {
    public final static String ID = makeID("Headshot");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;
    public boolean trig_deadon = false;

    public Headshot() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), EnumPatch.HERMIT_GUN));
    }

    @Override
    public void resetAttributes()
    {
        super.resetAttributes();
        this.trig_deadon = false;
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
            this.damage *= 2;
        }

        isDamageModified = damage != baseDamage;
    }

    public void applyPowers() {
        super.applyPowers();
        if (isDeadOnPos()) {
            this.damage *= 2;
            this.isDamageModified = true;
        }
    }

    public boolean isDeadOnPos()
    {
        if (this.trig_deadon)
        return true;

        if (!Wiz.p().hand.contains(this))
        return false;

        double hand_pos = (AbstractDungeon.player.hand.group.indexOf(this)+0.5);
        double hand_size = (AbstractDungeon.player.hand.size());
        double relative = Math.abs(hand_pos-hand_size/2);

        return (relative<1);
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard cardcopy = super.makeStatEquivalentCopy();

        if (AbstractDungeon.player != null)
        if (this.isDeadOnPos())
        ((Headshot)cardcopy).trig_deadon = true;

        return cardcopy;
    }

    //Upgraded stats.
    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}