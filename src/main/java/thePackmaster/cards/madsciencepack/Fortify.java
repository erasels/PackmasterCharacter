package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.FindCardForAddModifierAction;
import thePackmaster.cardmodifiers.madsciencepack.AddBlockModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Fortify extends AbstractPackmasterCard {
    public final static String ID = makeID("Fortify");

    public Fortify() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new FindCardForAddModifierAction(new AddBlockModifier(magicNumber),1,true, AbstractDungeon.player.hand, card->card.baseBlock>0));

    }

    public void upp() {
        upgradeBlock(3);
    }
}