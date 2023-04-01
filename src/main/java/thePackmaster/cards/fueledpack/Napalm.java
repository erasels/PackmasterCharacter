package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class Napalm extends AbstractFueledCard {
    public final static String ID = makeID(Napalm.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int MAGIC = 7;
    private static final int UPGRADE_MAGIC = 3;

    public Napalm() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ApplyPowerAction action = new ApplyPowerAction(m, adp(), new IgnitePower(m, magicNumber));
        atb(new ConsumeToDoAction(action));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
