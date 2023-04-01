package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class PoisonousSmoke extends AbstractFueledCard {
    public final static String ID = makeID(PoisonousSmoke.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 1;

    public PoisonousSmoke() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameAction action = new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractMonster m : Wiz.getEnemies())
                    applyToEnemyTop(m, new StrengthPower(m, -magicNumber));
                isDone = true;
            }
        };

        atb(new ConsumeToDoAction(action));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
