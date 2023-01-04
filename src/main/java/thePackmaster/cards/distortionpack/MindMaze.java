package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class MindMaze extends AbstractPackmasterCard {
    public final static String ID = makeID("MindMaze");
    // intellij stuff skill, self, uncommon, , , 11, 3, , 

    public MindMaze() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 11;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this,
                (effect, params)->{
                    if (effect > 0) {
                        for(int i = 0; i < effect; ++i)
                            this.addToBot(new GainBlockAction(p, p, params[0]));
                        atb(new MakeTempCardInDrawPileAction(new Dazed(), effect, false, true, false));
                    }
                    return true;
                }, block));
    }

    public void upp() {
        upgradeBlock(4);
    }
}