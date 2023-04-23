package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.cthulhupack.NamelessMistPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NamelessMist extends AbstractCthulhuCard {
    public final static String ID = makeID("NamelessMist");

    private static final int BLOCK_VALUE = 5;

    public NamelessMist() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);

        baseBlock = BLOCK_VALUE;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new NamelessMistPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}