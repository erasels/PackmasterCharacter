package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.DelayActionAction;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.CheapenModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.util.Wiz;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cheapen extends AbstractMadScienceCard implements AmplifyCard {
    public final static String ID = makeID("Cheapen");
    private static final int AMP_COST = 1;

    public Cheapen() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FindCardForAddModifierAction(new CheapenModifier(0),1,!upgraded, AbstractDungeon.player.drawPile, card->card.cost>0));
    }

    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        exhaust = false;
        //Add a delayed action that sets exhaust to true again after the UseCardAction has run through
        Wiz.atb(new DelayActionAction(new AbstractGameAction() {
            @Override
            public void update() {
                Cheapen.this.exhaust = true;
                isDone = true;
            }
        }));
    }

    @Override
    public int getAmplifyCost() {
        return AMP_COST;
    }
}