package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PerfectedBrick extends AbstractBrickCard {
    public final static String ID = makeID(PerfectedBrick.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 6;
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public PerfectedBrick() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage < 15)
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        else
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countCards();
        super.calculateCardDamage(mo);
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public void applyPowers() {
        int realBaseDamage = baseDamage;
        baseDamage += magicNumber * countCards();
        super.applyPowers();
        baseDamage = realBaseDamage;
        isDamageModified = damage != baseDamage;
    }

    public static int countCards() {
        int count = 0;

        for (AbstractCard c : adp().hand.group)
            if (c.cost == -2)
                count++;

        for (AbstractCard c : adp().discardPile.group)
            if (c.cost == -2)
                count++;

        for (AbstractCard c : adp().drawPile.group)
            if (c.cost == -2)
                count++;

        return count;
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
