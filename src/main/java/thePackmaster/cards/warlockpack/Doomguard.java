package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Doomguard extends AbstractPackmasterCard {
    public final static String ID = makeID(Doomguard.class.getSimpleName());

    private static final int COST = 2;

    public Doomguard() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        baseDamage = 21;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        this.addToBot(new ExhaustAction(2, true, false, false));
    }

    @Override
    public void upp() {
        upgradeDamage(8);
    }
}
