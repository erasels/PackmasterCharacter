package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TotalWeakness extends AbstractRippableCard {
    public final static String ID = makeID("TotalWeakness");

    public TotalWeakness() {
        this(null, null);
    }

    public TotalWeakness(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = block = 6;
        baseMagicNumber = magicNumber = 2;
        if (artCard == null && textCard == null) {
            setRippedCards(new TotalWeaknessArt(this), new TotalWeaknessText(this));
        } else if(artCard == null){
            setRippedCards(new TotalWeaknessArt(this), textCard);
        } else {
            setRippedCards(artCard, new TotalWeaknessText(this));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(this::applyWeak);
    }

    private void applyWeak(AbstractMonster m){
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }
}
