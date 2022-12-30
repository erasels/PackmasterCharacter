/*
package thePackmaster.cards.demopack;

import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class BurningPocket extends AbstractPackmasterCard {
    public final static String ID = makeID("BurningPocket");
    // intellij stuff skill, self_and_enemy, common, , , 6, 2, 1, 1

    public BurningPocket() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = 6;
        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
        cardsToPreview = new Miracle();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void triggerOnExhaust() {
        makeInHand(new Miracle());
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}

 */