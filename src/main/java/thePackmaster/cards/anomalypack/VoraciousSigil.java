package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.VoraciousSigilAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class VoraciousSigil extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("VoraciousSigil");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 2;
    public CardGroup absorbedCards;

    public VoraciousSigil() {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        absorbedCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    }

    @Override
    public void upp() {
        this.isInnate=true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VoraciousSigilAction(this));
    }



}
