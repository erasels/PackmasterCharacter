package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.clawpack.AddClawTagAndMakeClawModifier;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MutateClaw extends AbstractClawCard {
    public final static String ID = makeID("MutateClaw");

    public MutateClaw() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new GhostClaw();
        tags.add(CLAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FindCardForAddModifierAction(new AddClawTagAndMakeClawModifier(magicNumber), 1, false, AbstractDungeon.player.hand, card -> card.type == CardType.ATTACK));

    }

    public void upp() {
        exhaust = false;
    }
}