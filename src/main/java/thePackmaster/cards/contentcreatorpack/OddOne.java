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

public class OddOne extends AbstractContentCard {
    public final static String ID = makeID("OddOne");

    public OddOne() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 11;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO - This should highlight the effect currently active in the text, like old Champ Combo.
        //Otherwise you have no idea which one is going to happen.

        int x = AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1;
        if (x % 2 == 0) {
            atb(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        } else {
            atb(new DrawCardAction(magicNumber));
        }
    }

    public void upp() {
        upgradeDamage(4);
    }
}