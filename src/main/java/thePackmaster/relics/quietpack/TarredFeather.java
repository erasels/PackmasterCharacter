package thePackmaster.relics.quietpack;

import basemod.BaseMod;
import basemod.helpers.CardPowerTip;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.quietpack.Weight;
import thePackmaster.packs.QuietPack;
import thePackmaster.relics.AbstractPackmasterRelic;

public class TarredFeather extends AbstractPackmasterRelic {
    private static final AbstractCard PREVIEW = new Weight();
    public static final String ID = SpireAnniversary5Mod.makeID("TarredFeather");

    public TarredFeather() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT, QuietPack.ID);
        if(tips.stream().noneMatch(t->t instanceof CardPowerTip)) {
            tips.add(new CardPowerTip(PREVIEW));
        }
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

