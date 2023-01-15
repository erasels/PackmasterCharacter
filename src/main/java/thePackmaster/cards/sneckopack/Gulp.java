package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Gulp extends AbstractPackmasterCard {

    public final static String ID = makeID("Gulp");
    private static final int DMG = 17, UPG_DMG = 5;

    public Gulp() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                for (int i = 0; i < Wiz.getLogicalCardCost(Gulp.this); i++) {
                    Wiz.doDmg(m, Gulp.this, AttackEffect.POISON, true);
                }
                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
