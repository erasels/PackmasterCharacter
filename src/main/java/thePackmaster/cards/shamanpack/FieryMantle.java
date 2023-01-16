package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.FieryMantlePower;

public class FieryMantle extends AbstractShamanCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FieryMantle");
    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPGRADE_BLOCK = 2;
    private static final int IGNITE_THORNS = 2;
    private static final int UPGRADE_IGNITE_THORNS = 1;

    public FieryMantle() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = IGNITE_THORNS;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
        this.upgradeMagicNumber(UPGRADE_IGNITE_THORNS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new FieryMantlePower(p, this.magicNumber)));
    }
}
