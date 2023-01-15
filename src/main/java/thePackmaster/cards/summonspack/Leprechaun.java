package thePackmaster.cards.summonspack;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.summonspack.JinxPower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class Leprechaun extends AbstractPackmasterCard {
    public final static String ID = makeID(Leprechaun.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 2;

    public Leprechaun() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.Leprechaun()));
        applyToEnemy(m, new JinxPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
