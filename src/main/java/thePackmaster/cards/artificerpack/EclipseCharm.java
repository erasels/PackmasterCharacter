package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class EclipseCharm extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("EclipseCharm");

    private int counter;

    public EclipseCharm(){
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void onPlayedNeighbor(AbstractCard playedCard, AbstractMonster monster) {
        counter++;
        if (counter >= magicNumber) {
            addToBot(new ApplyPowerAction(Wiz.adp(),Wiz.adp(),new StrengthPower(Wiz.adp(),1)));
            addToBot(new ApplyPowerAction(Wiz.adp(),Wiz.adp(),new DexterityPower(Wiz.adp(),1)));
            counter = 0;
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (!upgraded) {
            counter = 0;
        }
    }
}
