package thePackmaster.cards.madsciencepack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.CheapenModifier;
import thePackmaster.cardmodifiers.madsciencepack.PlayCardModifier;

import static thePackmaster.SpireAnniversary5Mod.ISCARDMODIFIED;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Attach extends AbstractMadScienceCard {
    public final static String ID = makeID("Attach");

    public Attach() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean handGood = false;
        if (p.hand.size() > 0 && p.discardPile.size() > 0){
            for (AbstractCard c:p.hand.group){
                if (!c.hasTag(ISCARDMODIFIED) && c.cost>-2 && c.type != CardType.POWER){
                    handGood = true;
                    break;
                }
            }
            if (handGood){
                for (AbstractCard c:p.discardPile.group){
                    if (!c.hasTag(ISCARDMODIFIED) && c.cost>-2){
                        return super.canUse(p,m);
                    }
                }
            }
        }
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            att(new ExhaustSpecificCardAction(cards.get(0), p.hand, true));
            att(new FindCardForAddModifierAction(new PlayCardModifier(magicNumber, cards.get(0)),1,false, AbstractDungeon.player.discardPile, card->card.cost!=-2));

        }));

    }

    public void upp() {
        upgradeBaseCost(0);
    }
}