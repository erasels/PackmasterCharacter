package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import thePackmaster.actions.sneckopack.RandomizeCostAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SneckoEyes extends AbstractPackmasterCard {


    public final static String ID = makeID("SneckoEyes");

    public SneckoEyes() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new ConfusionPower(p)));
        addToBot(new ApplyPowerAction(p,p, new DrawPower(p, 2)));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
