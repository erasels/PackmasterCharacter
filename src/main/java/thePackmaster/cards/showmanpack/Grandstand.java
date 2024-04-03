package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Grandstand extends AbstractPackmasterCard {
    public final static String ID = makeID("Grandstand");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Grandstand() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedBluePower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}