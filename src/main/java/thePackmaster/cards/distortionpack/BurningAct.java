package thePackmaster.cards.distortionpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FilteredExhaustAction;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class BurningAct extends AbstractPackmasterCard {
    public final static String ID = makeID("BurningAct");
    // intellij stuff skill, none, uncommon, , , , , 4,

    private static final int EXHAUST = 2;

    public BurningAct() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(this.magicNumber,
                new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;

                        List<AbstractCard> drawnCards = new ArrayList<>(DrawCardAction.drawnCards);
                        if (drawnCards.size() <= EXHAUST) {
                            for (AbstractCard c : drawnCards) {
                                att(new ExhaustSpecificCardAction(c, p.hand));
                            }
                            return;
                        }

                        if (BurningAct.this.upgraded) {
                            //select exhaust
                            atb(new FilteredExhaustAction(EXHAUST, false, false, false)
                                    .filter(drawnCards::contains));
                        }
                        else {
                            //random exhaust
                            for (int i = 0; i < EXHAUST; ++i) {
                                AbstractCard c = drawnCards.remove(AbstractDungeon.cardRandomRng.random(drawnCards.size() - 1));
                                att(new ExhaustSpecificCardAction(c, p.hand));
                            }
                        }
                    }
                }));
    }

    public void upp() {
        uDesc();
    }
}