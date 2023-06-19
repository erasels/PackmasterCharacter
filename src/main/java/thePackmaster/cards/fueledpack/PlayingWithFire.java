package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.PlayingWithFireAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PlayingWithFire extends AbstractFueledCard {
    public final static String ID = makeID(PlayingWithFire.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    public static final int SIDES = 8;
    public static final int IGNITE_THRESHOLD = 7;

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public PlayingWithFire() {
        super(ID, COST, TYPE, RARITY, TARGET);
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
