package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EmbraceHollowing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("EmbraceHollowing");
    // intellij stuff power, self, rare, , , , , 5, 3

    public EmbraceHollowing() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded){
            Wiz.applyToSelfTop(new StrengthPower(p, 2));
        }
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().powers.stream()
                        .filter(pow -> pow.type == AbstractPower.PowerType.DEBUFF)
                        .forEach(pow -> Wiz.applyToSelf(new StrengthPower(p, 2)));
                isDone = true;
            }
        });
    }

    public void upp() {
    }
}