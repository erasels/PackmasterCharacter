package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BleedItOut extends AbstractDimensionalCardVault {
    public final static String ID = makeID("BleedItOut");

    public BleedItOut() {
        super(ID, 1, CardRarity.RARE, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 4;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        Wiz.applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (m.hasPower(PoisonPower.POWER_ID)) {
                    m.getPower(PoisonPower.POWER_ID).atStartOfTurn();
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}