package thePackmaster.cards.marisapack.deprecated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.marisapack.AbstractMarisaCard;
import thePackmaster.powers.marisapack.EarthlightRayPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
public class EarthlightRay extends AbstractMarisaCard {
    public final static String ID = makeID(EarthlightRay.class.getSimpleName());
    private static final int UPG_COST = 2, MAGIC = 3;

    public EarthlightRay() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new EarthlightRayPower(magicNumber));
    }

    public void upp() {
        upgradeBaseCost(UPG_COST);
    }
}
