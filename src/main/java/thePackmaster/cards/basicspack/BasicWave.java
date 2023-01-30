package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Rummage;
import thePackmaster.cards.Strike;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicWave extends AbstractMultipleCardPreviewCard {
    public final static String ID = makeID("BasicWave");

    public BasicWave() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "basics");
        this.cardToPreview.add(new Strike());
        this.cardToPreview.add(new Defend());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i<this.cardToPreview.size(); i++){
            AbstractCard c = this.cardToPreview.get(i);
            AbstractDungeon.player.limbo.addToBottom(c);
            c.target_x = Settings.WIDTH / 2.0F - 150 * this.cardToPreview.size() + 300 * i;
            c.target_y = Settings.HEIGHT / 2.0F;
            if (m != null)
                c.calculateCardDamage(m);
            c.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, m, c.energyOnUse, true, true), true);
        }
    }

    public void upp(){
        for(AbstractCard c : this.cardToPreview) {
            c.upgrade();
        }
    }
}
