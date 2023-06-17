package thePackmaster.cards.gowiththeflowpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.p;

public class StormFront extends AbstractHydrologistCard {
    public final static String ID = makeID("StormFront");

    public StormFront() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, Subtype.STEAM);
        damage = baseDamage = 30;
        magicNumber = baseMagicNumber = 10;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, damage);
    }

    @Override
    public void triggerOnManualDiscard() {
        baseDamage += magicNumber;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(Wiz.discardPile().contains(StormFront.this)) {
                    addToTop(new MoveCardsAction(p().hand, p().discardPile, c -> c == StormFront.this));
                } else if (Wiz.drawPile().contains(StormFront.this)) {
                    //Fix Storm Front not returning to hand after being discarded if it was shuffled into the draw pile again due to wonky action ordering
                    addToTop(new MoveCardsAction(p().hand, p().drawPile, c -> c == StormFront.this));
                }

                isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}