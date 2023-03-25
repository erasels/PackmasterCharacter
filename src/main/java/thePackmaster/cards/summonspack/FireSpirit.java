package thePackmaster.cards.summonspack;

import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class FireSpirit extends AbstractSummonsCard {
    public final static String ID = makeID(FireSpirit.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 9;
    private static final int UPGRADE_BLOCK = 4;

    private final TooltipInfo igniteTip = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1]);

    public FireSpirit() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(igniteTip);
        return  list;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.FireSpirit()));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}
