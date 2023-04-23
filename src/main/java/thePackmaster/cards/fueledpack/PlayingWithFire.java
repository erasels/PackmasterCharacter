package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.PlayingWithFireAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class PlayingWithFire extends AbstractFueledCard {
    public final static String ID = makeID(PlayingWithFire.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;
    public static final int SIDES = 6;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;

    public PlayingWithFire() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PlayingWithFireAction(SIDES, magicNumber));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
