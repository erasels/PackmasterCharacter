package thePackmaster.cards.summonspack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.adp;

public class SummonBrambles extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonBrambles.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 10;
    private static final int UPGRADE_BLOCK = 3;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public SummonBrambles() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new ThornsPower(adp(), magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upMagic(UPGRADE_MAGIC);
    }
}
