package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.ApplyFrosbiteModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Winterize extends AbstractPackmasterCard {
    public final static String ID = makeID("Winterize");

    public Winterize() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChannelAction(new Frost()));
        addToBot(new ChannelAction(new Frost()));
        addToBot(new ChannelAction(new Frost()));
        addToBot(new FindCardForAddModifierAction(new ApplyFrosbiteModifier(magicNumber),magicNumber,false, AbstractDungeon.player.drawPile, card->card.target==CardTarget.ENEMY));

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}