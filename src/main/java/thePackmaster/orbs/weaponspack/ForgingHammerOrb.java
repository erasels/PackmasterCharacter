package thePackmaster.orbs.weaponspack;

import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForgingHammerOrb extends AbstractWeaponOrb {

    public static final String ID = makeID("ForgingHammerOrb");
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ID);
    public static final String NAME = orbStrings.NAME;
    public static final String[] DESCRIPTION = orbStrings.DESCRIPTION;

    public ForgingHammerOrb(int attack, int durability, boolean justAddedUsingAttackCard) {
        super(ID, NAME, DESCRIPTION[0], getOrbImagePath(ID), attack, durability, justAddedUsingAttackCard);
        this.attack = attack;
        this.durability = durability;
    }

    @Override
    public void effectOnUse() {
        AbstractDungeon.actionManager.addToBottom(new UpgradeRandomCardAction());
    }

}
