package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.boardgamepack.PerceptionCheckPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerceptionCheck extends AbstractBoardCard {
    public final static String ID = makeID(PerceptionCheck.class.getSimpleName());

    public PerceptionCheck() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PerceptionCheckPower(p, 1), 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}