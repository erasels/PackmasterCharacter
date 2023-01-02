package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.PyromasteryPower;

public class Pyromastery extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Pyromastery");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 0;
    private static final int IGNITE_BONUS = 2;
    private static final int UPGRADE_IGNITE_BONUS = 1;

    public Pyromastery() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = IGNITE_BONUS;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_IGNITE_BONUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PyromasteryPower(p, this.magicNumber)));
    }
}
