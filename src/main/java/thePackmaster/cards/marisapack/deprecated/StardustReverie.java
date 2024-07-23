package thePackmaster.cards.marisapack.deprecated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.marisapack.AbstractMarisaCard;
import thePackmaster.powers.marisapack.StardustReveriePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
public class StardustReverie extends AbstractMarisaCard {
    public final static String ID = makeID(StardustReverie.class.getSimpleName());
    private static final int MAGIC = 3;
    public static final int NEW_COST = 0;

    public StardustReverie() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StardustReveriePower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(NEW_COST);
    }
}
