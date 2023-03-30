package thePackmaster.cards.orbpack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.entropy.Oblivion;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Emptiness extends AbstractOrbCard {
    public final static String ID = makeID("Emptiness");
    private static TooltipInfo ruinTip;

    public Emptiness() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;

        showEvokeValue = true;
        showEvokeOrbCount = magicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        showEvokeOrbCount = magicNumber;
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> list = new ArrayList<>();
        if(ruinTip == null) {
            ruinTip = new TooltipInfo(BaseMod.getKeywordProper(makeID("ruin")), BaseMod.getKeywordDescription(makeID("ruin")));
        }
        list.add(ruinTip);
        return  list;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, 1, false));
        for (int i = 0; i < this.magicNumber; ++i)
            atb(new ChannelAction(new Oblivion()));
    }

    public void upp() {
        upgradeMagicNumber(1);
        showEvokeOrbCount = magicNumber;
    }
}