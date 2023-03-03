package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Shakedown extends AbstractIntrigueCard {
    public final static String ID = makeID("Shakedown");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Shakedown() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public boolean hasRarity(CardRarity rar) {
        Iterator var1 = Wiz.p().hand.group.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            if (c.rarity == rar && c.color != CardColor.CURSE) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
                Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));

                if (hasRarity(CardRarity.COMMON) || hasRarity(CardRarity.BASIC) || hasRarity(CardRarity.SPECIAL))
                    Wiz.atb(new DrawCardAction(1));
                if (hasRarity(CardRarity.UNCOMMON))
                    Wiz.atb(new DrawCardAction(1));
                if (upgraded && hasRarity(CardRarity.RARE))
                    Wiz.atb(new DrawCardAction(1));
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}