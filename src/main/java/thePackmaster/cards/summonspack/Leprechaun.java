package thePackmaster.cards.summonspack;

import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.boardgamepack.DicePower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.DICE_KEY;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class Leprechaun extends AbstractSummonsCard {
    public final static String ID = makeID(Leprechaun.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 3;

    private final TooltipInfo diceTip = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1]);
    private final TooltipInfo jinxTip = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[2], cardStrings.EXTENDED_DESCRIPTION[3]);

    public Leprechaun() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.Leprechaun()));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(diceTip);
        list.add(jinxTip);
        return  list;
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
    }
}
