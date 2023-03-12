package thePackmaster.cards.scrypluspack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import org.apache.commons.lang3.StringUtils;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.patches.scryplus.CardSeenScriedInterface;

import java.util.ArrayList;
import java.util.List;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Prosperity extends AbstractScryPlusCard
        implements CardSeenScriedInterface {

    public final static String ID = makeID(Prosperity.class.getSimpleName());
    public Prosperity() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setMN(2);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        addToBot(new DrawCardAction(magicNumber));
    }

    @Override
    public void onSeenScried()
    {
        AbstractPlayer p = player;
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1)));
    }

    @Override
    public List<TooltipInfo> getCustomTooltips()
    {
        List<TooltipInfo> tips = new ArrayList<>();
        tips.add(new TooltipInfo(StringUtils.capitalize(GameDictionary.SCRY.NAMES[0]), GameDictionary.SCRY.DESCRIPTION));
        return tips;
    }
}
