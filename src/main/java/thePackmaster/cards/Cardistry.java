package thePackmaster.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ReboundPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cardistry extends AbstractPackmasterCard {
    public final static String ID = makeID("Cardistry");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Cardistry() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new ReboundPower(p), 1));
    }

    public void upp() {
        upgradeBlock(2);
    }
}