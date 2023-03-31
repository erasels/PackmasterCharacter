package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.overwhelmingpack.FreeSkillPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Lariat extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Lariat");

    public Lariat() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = this.damage = 5;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToSelf(new FreeSkillPower(p, 1));
    }

    public void upp() {
        upgradeDamage(2);
    }
}