package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.shuffleIn;

public class StartingGun extends AbstractJockeyCard {
    public final static String ID = makeID("StartingGun");
    // intellij stuff attack, all_enemy, common, 6, 1, , , 2, 1

    public StartingGun() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Horse();
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        shuffleIn(new Horse(), magicNumber);
    }

    public void upp() {
        upgradeDamage(3);
    }
}