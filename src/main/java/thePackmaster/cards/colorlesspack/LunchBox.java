package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.colorlesspack.LunchBoxPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class LunchBox extends AbstractColorlessPackCard {
    public final static String ID = makeID("LunchBox");
    // intellij stuff skill, self, uncommon, , , 6, 3, , 

    public LunchBox() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new LunchBoxPower());
    }

    public void upp() {
        upgradeBlock(3);
    }
}