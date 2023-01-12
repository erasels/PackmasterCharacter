package thePackmaster.cards.pixiepack;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.pixiepack.DimensionBreachAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DimensionBreach extends AbstractPixieCard {
    public final static String ID = makeID("DimensionBreach");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final int baseMgk = 3;
    private static final int upgradeMgk = 5;

    public DimensionBreach() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = baseMgk;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        List<TooltipInfo> tooltips = new ArrayList<>();
        tooltips.add(new TooltipInfo(EXTENDED_DESCRIPTION[0], EXTENDED_DESCRIPTION[1]));
        return tooltips;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i = 0; i < this.magicNumber; i++)
        {
            addToBot(new DimensionBreachAction(abstractMonster));
        }
    }
}
