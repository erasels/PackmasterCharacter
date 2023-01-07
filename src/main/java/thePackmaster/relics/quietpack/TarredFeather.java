package thePackmaster.relics.quietpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.quietpack.Weight;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.relics.HandyHaversack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TarredFeather extends AbstractPackmasterRelic {
    public static final String ID = makeID("TarredFeather");

    public TarredFeather() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }

    @Override
    public void obtain() {
        BaseMod.MAX_HAND_SIZE +=2;
    }

    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new Weight(), 2));
    }
}

