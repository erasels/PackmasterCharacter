package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Shackles extends AbstractEvolveCard {
    public final static String ID = makeID(Shackles.class.getSimpleName());

    private static final int BLOCK = 10;
    private static final int UPGRADE_BLOCK = 3;
    private static final int UPGRADE_BLOCK_FINAL = 4;
    private static final int EVOLVE_COST = 2;

    public Shackles() {
        this(false);
    }
    public Shackles(boolean isPreviewCard) {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, isPreviewCard);
        selfRetain = true;
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = EVOLVE_COST;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (this.timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            applyToSelf(new BarricadePower(p));
        }
    }

    public void upp() {
        if (timesUpgraded == AbstractEvolveCard.MAX_UPGRADES) {
            upgradeBlock(UPGRADE_BLOCK_FINAL);
            exhaust = true;
        } else {
            upgradeBlock(UPGRADE_BLOCK);
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.costForTurn >= magicNumber) {
            evolve();
        }
    }

    @Override
    protected AbstractCard getPreviewCard() {
        return new Shackles(true);
    }
}