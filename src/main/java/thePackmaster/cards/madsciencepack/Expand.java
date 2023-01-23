package thePackmaster.cards.madsciencepack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.ExpandModifier;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Expand extends AbstractMadScienceCard {
    public final static String ID = makeID("Expand");

    public Expand() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FindCardForAddModifierAction(new ExpandModifier(),1,false, AbstractDungeon.player.hand));

    }

    public void upp() {
        exhaust = false;
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
    }
}