package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.aggressionpack.InnerFuryPower;
import thePackmaster.stances.aggressionpack.AggressionStance;

public class InnerFury extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("InnerFury");
    private static final int COST = 1;
    private static final int TEMP_STRENGTH_AND_BLOCK = 3;
    private static final int UPGRADE_TEMP_STRENGTH_AND_BLOCK = 2;

    public InnerFury() {
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = TEMP_STRENGTH_AND_BLOCK;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_TEMP_STRENGTH_AND_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new InnerFuryPower(p, this.magicNumber)));
        this.addToBot(new ChangeStanceAction(new AggressionStance()));
    }
}
