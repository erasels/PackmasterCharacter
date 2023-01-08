package thePackmaster.relics.quietpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.quietpack.Weight;
import thePackmaster.packs.QuietPack;
import thePackmaster.relics.AbstractPackmasterRelic;
import thePackmaster.relics.HandyHaversack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TarredFeather extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("TarredFeather");

    public TarredFeather() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, QuietPack.ID);
    }

    @Override
    public void onEquip() {
        BaseMod.MAX_HAND_SIZE +=2;
    }

    @Override
    public void onUnequip() {
        BaseMod.MAX_HAND_SIZE -=2;
    }

    public void atBattleStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction(new Weight(), 2));
    }
}

