package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class TitansStrength extends AbstractAggressionCard {
    public static final String ID = SpireAnniversary5Mod.makeID("TitansStrength");
    private static final int COST = 2;
    private static final int BLOCK = 14;
    private static final int UPGRADE_BLOCK = 4;
    private static final int STRENGTH = 1;

    public TitansStrength() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
        this.magicNumber = this.baseMagicNumber = STRENGTH;
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
    }
}
