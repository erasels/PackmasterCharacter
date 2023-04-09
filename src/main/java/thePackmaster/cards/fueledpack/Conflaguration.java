package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.orbs.spherespack.Blaze;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class Conflaguration extends AbstractFueledCard {
    public final static String ID = makeID(Conflaguration.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = 3;

    public Conflaguration() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ApplyPowerAction igniteAction = new ApplyPowerAction(m, adp(), new IgnitePower(m, magicNumber));
        ChannelAction blazeAction = new ChannelAction(new Blaze());
        AbstractGameAction action = new AbstractGameAction() {
            @Override
            public void update() {
                att(blazeAction);
                att(igniteAction);
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
