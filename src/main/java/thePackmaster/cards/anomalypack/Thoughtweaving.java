package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.anomalypack.ThoughtweavingPower;

public class Thoughtweaving extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Thoughtweaving");
    private static final int COST = 2;

    public Thoughtweaving() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber=1;
    }

    @Override
    public void upp() {  this.upgradeBaseCost(1); }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new ThoughtweavingPower(p, magicNumber), magicNumber));
    }
}
