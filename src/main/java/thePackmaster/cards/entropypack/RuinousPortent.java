package thePackmaster.cards.entropypack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.orbs.entropy.Oblivion;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class RuinousPortent extends AbstractEntropyCard {
    public final static String ID = makeID("RuinousPortent");
    private static TooltipInfo ruinTip;

    public RuinousPortent() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 0;
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
        atb(new EasyXCostAction(this,
                (effect, params)->{
                    effect += params[0];
                    for(int i = 0; i < effect; ++i) {
                        att(new ChannelAction(new Oblivion()));
                    }
                    return true;
                }, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}