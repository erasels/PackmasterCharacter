package thePackmaster.cards.summonspack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.atb;

public class EvilSpirit extends AbstractPackmasterCard {
    public final static String ID = makeID(EvilSpirit.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    private static final String SADISTIC_TIP = "Sadistic\u00a0Nature";

    public EvilSpirit() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.EvilSpirit()));
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> extraTooltips = new ArrayList();
        extraTooltips.add(new TooltipInfo(BaseMod.getKeywordTitle(SADISTIC_TIP), BaseMod.getKeywordDescription(SADISTIC_TIP)));
        return extraTooltips;
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
