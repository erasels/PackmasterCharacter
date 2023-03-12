package thePackmaster.cards.scrypluspack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.patches.scryplus.CardSeenScriedInterface;
import thePackmaster.patches.scryplus.OnBeingScriedInterface;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FutureShield extends AbstractScryPlusCard
        implements OnBeingScriedInterface,
        CardSeenScriedInterface {

    public final static String ID = makeID(FutureShield.class.getSimpleName());
    public FutureShield() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        setBlock(10);
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster)
    {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void onBeingScried()
    {
        applyPowers();
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new GainBlockAction(p, p, block));
        resetAttributes();
    }

    @Override
    public void onSeenScried()
    {
        scryFlash();
        applyPowers();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(StringUtils.capitalize(GameDictionary.SCRY.NAMES[0]), GameDictionary.SCRY.DESCRIPTION));
        return tips;
    }
}
