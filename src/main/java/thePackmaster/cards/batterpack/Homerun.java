package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Homerun extends AbstractPackmasterCard {
    public final static String ID = makeID("Homerun");

    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 4;

    public Homerun() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isCrit(3))
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        else AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

        if (isCrit(2)) {
            this.damage *= 1.5;
        }

        isDamageModified = damage != baseDamage;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isCrit(2)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (isCrit(2)) {
            this.damage *= 1.5;
            this.isDamageModified = true;
        }
    }

    public boolean isCrit(int amount)
    {
        int cards = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        ArrayList<AbstractCard> list = AbstractDungeon.actionManager.cardsPlayedThisTurn;
        if (!list.isEmpty() && list.get(list.size() - 1) == this)
            cards--;

        if (cards >= amount)
        return true;

        return false;
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}