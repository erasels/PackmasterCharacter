package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.prismaticpack.PremiumSelectionAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class PremiumSelection extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PremiumSelection");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int CHOICES = 3;
    private static final int UPGRADE_CHOICES = 1;

    public PremiumSelection() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = CHOICES;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_CHOICES);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new PremiumSelectionAction(this.magicNumber));
    }
}
