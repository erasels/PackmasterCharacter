package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class SubtleKnife extends AbstractPackmasterCard implements AmplifyCard {
    public final static String ID = makeID("SubtleKnife");
    public boolean wasJustPlayed = false;

    public SubtleKnife() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = 5;
        magicNumber = baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        wasJustPlayed = true;
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        applyToEnemy(m, new DistortionPower(m, p, magicNumber));
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public void triggerOnExhaust() {
        if (wasJustPlayed) {
            wasJustPlayed = false;
            atb(new DiscardSpecificCardAction(this, adp().exhaustPile));
            atb(new WaitAction(0.1F));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    unfadeOut();
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.exhaust = shouldAmplify(this);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.exhaust = shouldAmplify(this);
    }
}