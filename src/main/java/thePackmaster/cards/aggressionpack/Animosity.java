package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.aggressionpack.AnimosityPower;

public class Animosity extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Animosity");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    private static final int STRENGTH = 1;
    private static final int UPGRADE_STRENGTH = 1;

    public Animosity() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = STRENGTH;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_STRENGTH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AnimosityPower(p, this.magicNumber)));
    }
}
