package thePackmaster.orbs.weaponspack;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.OrbStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CatONineTailsOrb extends AbstractWeaponOrb {

    public static final String ID = makeID("CatONineTailsOrb");
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ID);
    public static final String NAME = orbStrings.NAME;
    public static final String[] DESCRIPTION = orbStrings.DESCRIPTION;
    public static final int ATTACK_UPGRADE_ON_USE = 2;

    public CatONineTailsOrb(int attack, int durability, boolean justAddedUsingAttackCard) {
        super(ID, NAME, DESCRIPTION[0], getOrbImagePath(ID), attack, durability, justAddedUsingAttackCard);
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    public void effectOnUse() {
        this.upgrade(ATTACK_UPGRADE_ON_USE, 0);
    }
}
