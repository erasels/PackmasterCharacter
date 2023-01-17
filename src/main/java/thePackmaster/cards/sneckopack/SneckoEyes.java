package thePackmaster.cards.sneckopack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConfusionPower;
import com.megacrit.cardcrawl.powers.DrawPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SneckoEyes extends AbstractSneckoCard implements StartupCard {


    public final static String ID = makeID("SneckoEyes");

    public SneckoEyes() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(!upgraded)
            addToBot(new ApplyPowerAction(p,p, new ConfusionPower(p)));
        addToBot(new ApplyPowerAction(p,p, new DrawPower(p, 2)));
    }

    public void upp() {
    }

    @Override
    public boolean atBattleStartPreDraw() {
        if(upgraded) {
            addToBot(new ApplyPowerAction(Wiz.p(),Wiz.p(), new ConfusionPower(Wiz.p())));
        }
        return upgraded;
    }
}
