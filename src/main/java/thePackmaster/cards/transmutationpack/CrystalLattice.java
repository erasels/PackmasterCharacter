package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class CrystalLattice extends AbstractHydrologistCard {
    public final static String ID = makeID("CrystalLattice");
    // intellij stuff SKILL, NONE, UNCOMMON, , , , , 2, 1

    public CrystalLattice() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.ICE);
        baseMagicNumber = magicNumber = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TransmuteCardAction((oldCard, newCard) -> {
            for (int i = 0; i < magicNumber; ++i) {
                addToTop(new MakeTempCardInHandAction(newCard));
            }
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}