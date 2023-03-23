package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.colorlesspack.BunkerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Bunker extends AbstractColorlessPackCard {
    public final static String ID = makeID("Bunker");
    // intellij stuff skill, self, common, , , 12, 4, , 

    public Bunker() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 14;
        cardsToPreview = new GolfBall();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BunkerPower());
    }

    public void upp() {
        upgradeBlock(4);
    }
}