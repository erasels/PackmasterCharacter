package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.colorlesspack.ColorlessMasteryPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ColorlessMastery extends AbstractColorlessPackCard {
    public final static String ID = makeID("ColorlessMastery");
    // intellij stuff power, self, uncommon, , , , , , 

    public ColorlessMastery() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ColorlessMasteryPower());
    }

    public void upp() {
        isInnate = true;
    }
}