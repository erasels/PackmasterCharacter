package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ManaShield extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("ManaShield");

    public ManaShield() {
        super(ID, 2, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = 10;
        baseMagicNumber = magicNumber = 1;
        
        cardsToPreview = new Channel();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0; i < magicNumber; i++) {
            Wiz.atb(new MakeTempCardInHandAction(new Channel()));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}