package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardStorybook;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Knighthood extends AbstractDimensionalCardStorybook {
    public final static String ID = makeID("Knighthood");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public Knighthood() {
        super(ID, 3, CardRarity.RARE, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);

        exhaust = true;
        tags.add(CardTags.HEALING);
        isEthereal = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            AbstractCard q = cards.get(0);
            q.upgrade();

            AbstractDungeon.effectList.add(new UpgradeShineEffect(q.current_x, q.current_y));
            AbstractCard q2 = StSLib.getMasterDeckEquivalent(q);
            if (q2 != null){
                q2.upgrade();
            }
        }));
    }

    public void upp() {
        isEthereal = false;
        selfRetain = true;
    }
}