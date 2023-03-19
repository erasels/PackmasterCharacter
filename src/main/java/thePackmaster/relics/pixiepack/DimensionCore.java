package thePackmaster.relics.pixiepack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.PixiePack;
import thePackmaster.relics.AbstractPackmasterRelic;

public class DimensionCore extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("DimensionCore");

    public DimensionCore() {
        super(ID, RelicTier.SHOP, LandingSound.MAGICAL, PixiePack.ID);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        int count = AbstractDungeon.player.masterDeck.size();
        for (int i = 0; i < ((int)(count/10))*3; i++)
        {
            AbstractCard toAdd = PixiePack.pixieGenerate(null,null,null,null);
            addToBot(new MakeTempCardInDrawPileAction(toAdd,1,true,false,false,currentX,currentY));
        }
    }
    @Override
    public void atTurnStart() {
        addToTop(new com.megacrit.cardcrawl.actions.common.DrawCardAction(1));
    }

    public String getUpdatedDescription() {
        return String.format(DESCRIPTIONS[0]);
    }
}
