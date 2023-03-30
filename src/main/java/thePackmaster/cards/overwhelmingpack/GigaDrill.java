package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.jockeypack.Horse;
import thePackmaster.cards.sneckopack.Gulp;
import thePackmaster.powers.jockeypack.DerbyPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class GigaDrill extends AbstractOverwhelmingCard {
    public final static String ID = makeID("GigaDrill");

    public GigaDrill() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = Wiz.getLogicalCardCost(this), dmg = this.damage;
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < amt; i++) {
                    //att(new VFXAction(new ));
                    Wiz.doDmg(m, dmg, damageTypeForTurn, AttackEffect.SLASH_HEAVY, true, true);
                }
                isDone = true;
            }
        });
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                updateCost(1);
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}