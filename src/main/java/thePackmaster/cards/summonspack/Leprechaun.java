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
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;
    private static final int DICE_SIDES = 6;

    private final TooltipInfo diceTip = new TooltipInfo(cardStrings.EXTENDED_DESCRIPTION[0], cardStrings.EXTENDED_DESCRIPTION[1]);

    public Leprechaun() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.Leprechaun()));
        atb(new SFXAction(DICE_KEY,0.1f));
        for (int i = 0; i < magicNumber; i++)
            applyToSelf(new DicePower(adp(), DICE_SIDES, false));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(diceTip);
        return  list;
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
