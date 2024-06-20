package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class TheFool extends AbstractAstrologerCard {
    public final static String ID = makeID("TheFool");
    // intellij stuff skill, none, common, , , , , , 

    public TheFool() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        AnimatedCardsPatch.loadFrames(this, 28, 0.07f);

        this.damage = this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 3;

        isInnate = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}