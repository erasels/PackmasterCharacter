package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;

public class IridiumShield extends AbstractFueledCard implements OnConsumedCard {
    public final static String ID = makeID(IridiumShield.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 8;
    private static final int UPGRADE_BLOCK = 3;
    private static final int MAGIC = 2;

    public IridiumShield() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void OnConsumed() {
        blck();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}
