package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.artificerpack.DrawEnchantModifier;

public class GrantSwiftness extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("GrantSwiftness");

    public GrantSwiftness(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(magicNumber));
        for (AbstractCard c : getNeighbors()) {
            addToBot(new SimpleAddModifierAction(new DrawEnchantModifier(1), c));
        }
    }
}
