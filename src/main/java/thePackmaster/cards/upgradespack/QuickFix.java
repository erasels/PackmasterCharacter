package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.RandomUpgradeWithVfxAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class QuickFix extends AbstractBlacksmithCard {

    public final static String ID = makeID("QuickFix");

    public QuickFix() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new RandomUpgradeWithVfxAction(1, Wiz.adp().hand));
    }

    public void upp() {
        upgradeBlock(3);
    }

}
