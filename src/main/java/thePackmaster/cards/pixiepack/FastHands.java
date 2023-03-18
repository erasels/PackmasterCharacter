package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;
import thePackmaster.packs.PixiePack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FastHands extends AbstractPixieCard {
    public final static String ID = makeID("FastHands");

    public FastHands() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.retain = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> toDraw = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.player.drawPile.group
        ) {
            if (PixiePack.isForeign(c)) {
                toDraw.add(c);
            }
        }
        if (toDraw.size() > 0) {
            AbstractDungeon.actionManager.addToTop(new DrawSpecificCardAction(toDraw.get(AbstractDungeon.cardRng.random(0, toDraw.size() - 1))));
        }
    }
}
