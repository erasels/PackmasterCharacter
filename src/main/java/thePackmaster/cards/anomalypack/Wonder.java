package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.WonderAction;
import thePackmaster.util.Wiz;

public class Wonder extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Wonder");
    private static final int COST = 0;

    public Wonder() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DiscardAction(AbstractDungeon.player,AbstractDungeon.player, AbstractDungeon.player.hand.size(), true));
        Wiz.atb(new DrawCardAction(1, new WonderAction()));
    }
}
