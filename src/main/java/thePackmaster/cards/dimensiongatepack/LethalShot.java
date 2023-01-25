package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LethalShot extends AbstractDimensionalCard {
    public final static String ID = makeID("LethalShot");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public LethalShot() {
        super(ID, 2, CardRarity.COMMON, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ENEMY);
        baseDamage = 12;
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 1;


        setFrame("lethalshotframe.png");
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber));

        Wiz.applyToEnemy(m, new VulnerablePower(m, secondMagic, false));
    }

    public void upp() {
        upgradeMagicNumber(3);
        upgradeSecondMagic(1);
    }
}