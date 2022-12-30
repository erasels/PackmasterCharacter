package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Synergize extends AbstractPackmasterCard {
    public final static String ID = makeID("Synergize");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Synergize() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = 7;
        baseDamage = 7;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        //TODO Conditonal AOE
    }

    public void upp() {
        upgradeBlock(2);
        upgradeDamage(2);
    }
}