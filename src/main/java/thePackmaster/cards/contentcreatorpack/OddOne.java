package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class OddOne extends AbstractPackmasterCard {
    public final static String ID = makeID("OddOne");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , 1, 1

    public OddOne() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 11;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1;
        if (x % 2 == 0) {
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else {
            atb(new DrawCardAction(magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}