package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class Chronobooster extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("Chronobooster");


    public Chronobooster(){
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void onPlayedNeighbor(AbstractCard playedCard,AbstractMonster monster) {
        playedCard.exhaustOnUseOnce = true;
        if (!playedCard.purgeOnUse) {
            this.flash();
            AbstractMonster m = null;
            if (monster != null) {
                m = monster;
            }
            AbstractCard tmp = playedCard.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = playedCard.current_x;
            tmp.current_y = playedCard.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, playedCard.energyOnUse, true, true), true);
            if (!upgraded) {
                Wiz.atb(new ExhaustSpecificCardAction(this,Wiz.hand()));
            } else {
                Wiz.atb(new DiscardSpecificCardAction(this,Wiz.hand()));
            }
        }
    }
}
