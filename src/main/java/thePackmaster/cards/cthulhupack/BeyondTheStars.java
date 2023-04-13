package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BeyondTheStars extends AbstractCthulhuCard {
    public final static String ID = makeID("BeyondTheStars");

    public BeyondTheStars() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL);
        baseDamage = 7;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Lunacy();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        for (AbstractMonster q : Wiz.getEnemies()) {
            loseSanity(magicNumber);
        }

    }

    public void upp() {
        upgradeDamage(3);
    }
}