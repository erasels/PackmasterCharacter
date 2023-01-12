package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FragileShrug extends AbstractRippableCard {
    public final static String ID = makeID("FragileShrug");

    public FragileShrug() {
        this(null, null);
    }

    public FragileShrug(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = block = 7;
        baseMagicNumber = magicNumber = 1;
        if (artCard == null && textCard == null) {
            setRippedCards(new FragileShrugArt(this), new FragileShrugText(this));
        } else if(artCard == null){
            setRippedCards(new FragileShrugArt(this), textCard);
        } else {
            setRippedCards(artCard, new FragileShrugText(this));
        }
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void onRip() {
        super.onRip();
        atb(new DrawCardAction(magicNumber));
    }
}
