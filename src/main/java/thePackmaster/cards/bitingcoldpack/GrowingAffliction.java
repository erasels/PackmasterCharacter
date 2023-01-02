package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GrowingAffliction extends AbstractPackmasterCard {
    public final static String ID = makeID("GrowingAffliction");

    public GrowingAffliction() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.POISON);
    }

    public void upp() {
        upgradeDamage(2);
    }

    // Hey there! Welcome to my code!
    // This card returns to hand when the player applies a debuff
    // That functionality is in my OnApplyDebuffPatch!
    // If you want to see the code for it, go there ^^
}