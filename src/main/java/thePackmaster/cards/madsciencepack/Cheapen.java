package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ChangePlayedCardExhaustAction;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.CheapenModifier;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.util.Wiz;

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

    public void upp() {}

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ChangePlayedCardExhaustAction(this, false));
    }

    @Override
    public int getAmplifyCost() {
        return AMP_COST;
    }
}