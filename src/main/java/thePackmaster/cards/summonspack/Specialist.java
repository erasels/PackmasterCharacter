package thePackmaster.cards.summonspack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class Specialist extends AbstractSummonsCard {
    public final static String ID = makeID(Specialist.class.getSimpleName());
    private static final int COST = 0;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public Specialist() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FocusPower(adp(), magicNumber));
        applyToSelf(new StrengthPower(adp(), -1));
        applyToSelf(new DexterityPower(adp(), -1));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
