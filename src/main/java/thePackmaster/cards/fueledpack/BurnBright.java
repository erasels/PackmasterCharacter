package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.actions.fueledpack.BurnBrightAction;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.powers.fueledpack.BurnBrightPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class BurnBright extends AbstractFueledCard {
    public final static String ID = makeID(BurnBright.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = -1;

    public BurnBright() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb( new EasyXCostAction( this, (effect, params) -> {
            if (effect > 0)
                att(new BurnBrightAction(effect, params[0]));
            return true;
        }, upgraded ? 1 : 0));
    }

    @Override
    public void upp() {
    }
}
