package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.FloatAndRetainModifier;
import thePackmaster.cards.madsciencepack.AbstractMadScienceCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Lighten extends AbstractMadScienceCard {
    public final static String ID = makeID("Lighten");

    public Lighten() {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 20;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new FindCardForAddModifierAction(new FloatAndRetainModifier(magicNumber),1,false, AbstractDungeon.player.hand, card->card.cost>-1));

    }

    public void upp() {
        upgradeBlock(6);
    }
}