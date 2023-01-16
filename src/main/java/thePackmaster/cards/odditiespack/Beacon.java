package thePackmaster.cards.odditiespack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.makeInHand;

public class Beacon extends AbstractPackmasterCard implements StartupCard {
    public final static String ID = makeID("Beacon");
    // intellij stuff skill, self, common, , , 5, 3, , 

    public Beacon() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 6;
        cardsToPreview = new Safety();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        makeInHand(new Safety());
        return true;
    }

    public void upp() {
        upgradeBlock(3);
    }
}