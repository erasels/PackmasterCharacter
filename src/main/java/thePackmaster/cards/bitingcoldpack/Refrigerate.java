package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.bitingcoldpack.FrostbiteDamageAction;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Refrigerate extends BitingColdCard {
    public final static String ID = makeID("Refrigerate");

    public Refrigerate() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 11;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Checks for presence of Frostbite
        boolean shouldDoAdditionalEffect = upgraded && m.hasPower(FrostbitePower.POWER_ID);

        // Applies Frostbite
        applyToEnemy(m, new FrostbitePower(m, magicNumber));

        // Does additional effect if Frostbite was and is present
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPower frostbiteInstance = m.getPower(FrostbitePower.POWER_ID);
                if (shouldDoAdditionalEffect && frostbiteInstance != null) {
                    att(new FrostbiteDamageAction(m, frostbiteInstance, false));
                }
                this.isDone = true;
            }
        });
    }

    public void upp() {}
}