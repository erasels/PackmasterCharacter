package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cosmoscommandpack.RecallPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Recall extends AbstractCosmosCard {
    public final static String ID = makeID("Recall");

    public Recall() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RecallPower(p));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}