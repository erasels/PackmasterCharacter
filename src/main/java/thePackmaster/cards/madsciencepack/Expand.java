package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.RemoveExhaustGainEtherealModifier;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Expand extends AbstractMadScienceCard {
    public final static String ID = makeID("Expand");

    public Expand() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FindCardForAddModifierAction(new RemoveExhaustGainEtherealModifier(0),1,false, AbstractDungeon.player.discardPile));

    }

    public void upp() {
       upgradeBaseCost(0);
    }
}