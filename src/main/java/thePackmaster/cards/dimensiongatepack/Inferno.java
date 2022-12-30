package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.SelfDamageAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Inferno extends AbstractDimensionalCard {
    public final static String ID = makeID("Inferno");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public Inferno() {
        super(ID, 1, CardRarity.UNCOMMON, AbstractCard.CardType.ATTACK, AbstractCard.CardTarget.ALL);
        baseDamage = 10;
        exhaust = true;
        setFrame("infernoframe.png");
        isMultiDamage = true;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        //TODO - In practice this actual does still fire even if the combat ends!  Actually kinda cool mechanic though, should we make that happen?
        addToBot(new SelfDamageAction(new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));


    }

    public void upp() {
        upgradeBaseCost(0);
    }
}