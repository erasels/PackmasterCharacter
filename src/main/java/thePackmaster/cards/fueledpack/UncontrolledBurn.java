package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.fueledpack.ConsumeHandWithFollowupAction;

import java.util.function.Consumer;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class UncontrolledBurn extends AbstractFueledCard {
    public final static String ID = makeID(UncontrolledBurn.class.getSimpleName());
    private static final int COST = 3;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private final static int MAGIC = 2;
    private final static int UPGRADE_MAGIC = 1;

    public UncontrolledBurn() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<Integer> consumer = amount -> {
            for (int i = 0; i < amount; i++)
                att(new ApplyPowerAction(adp(), adp(), new StrengthPower(adp(), magicNumber)));
        };

        atb(new ConsumeHandWithFollowupAction(consumer));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
