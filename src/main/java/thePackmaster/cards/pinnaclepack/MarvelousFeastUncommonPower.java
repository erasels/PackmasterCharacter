package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pinnaclepack.Cooking;
import thePackmaster.powers.pinnaclepack.Cooking_;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MarvelousFeastUncommonPower extends AbstractPinnacleCard {

    public final static String ID = makeID("MarvelousFeastUncommonPower");
    private boolean fixUpgradeCheck = false;

    public MarvelousFeastUncommonPower() {
        super(ID, 2, AbstractCard.CardType.POWER, CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.fixUpgradeCheck){
            addToBot(new ApplyPowerAction(p, p, new Cooking_(p, 4)));
        }
        else {
            addToBot(new ApplyPowerAction(p, p, new Cooking(p, 4)));
        }
    }

    @Override
    public void upp() {
        upgradeBaseCost(3);
        this.fixUpgradeCheck = true;
    }

}
