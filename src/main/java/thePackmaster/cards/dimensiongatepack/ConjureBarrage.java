package thePackmaster.cards.dimensiongatepack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjureBarrage extends AbstractDimensionalCard {
    public final static String ID = makeID("ConjureBarrage");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public ConjureBarrage() {
        super(ID, 0, CardRarity.UNCOMMON, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);

        setFrame("conjurebarrageframe.png");
        exhaust = true;
        cardsToPreview = new MagicMissile();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c;
        c = new MagicMissile();
        if (upgraded){
            c.upgrade();
        }
        for (int i = 0; i < BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.group.size() + 1; i++) {
            addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
        }

    }

    public void upp() {
        uDesc();
        cardsToPreview.upgrade();
    }
}