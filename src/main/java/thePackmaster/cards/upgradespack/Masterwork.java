package thePackmaster.cards.upgradespack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class Masterwork extends AbstractPackmasterCard {

    public final static String ID = SpireAnniversary5Mod.makeID("Masterwork");
    private static final String SCREEN_MSG = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MasterworkScreen")).TEXT[0];

    public Masterwork() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(1,SCREEN_MSG,(list) -> addToBot(new SuperUpgradeAction(list,magicNumber))));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
