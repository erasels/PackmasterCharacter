package thePackmaster.cards.coresetpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Showoff extends AbstractPackmasterCard {
    public final static String ID = makeID("Showoff");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Showoff() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 2), 2));
    }

    public void upp() {
        upgradeBlock(4);
    }
}