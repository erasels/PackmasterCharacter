package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.NamelessMistPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ReinIn extends AbstractPackmasterCard {
    public final static String ID = makeID("ReinIn");
    // intellij stuff skill, self, uncommon, , , 2, 1, , 

    public ReinIn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new NamelessMistPower(p, block));
    }

    public void upp() {
        upgradeBlock(1);
    }
}