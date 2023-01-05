package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.CheapenModifier;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cheapen extends AbstractMadScienceCard {
    public final static String ID = makeID("Cheapen");

    public Cheapen() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FindCardForAddModifierAction(new CheapenModifier(0),1,!upgraded, AbstractDungeon.player.drawPile, card->card.cost!=0));
        //TODO - Amplify2: don't exhaust
    }

    public void upp() {

    }
}