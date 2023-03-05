package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.secretlevelpack.AchievementHunterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class AchievementHunter extends AbstractPackmasterCard {
    public final static String ID = makeID("AchievementHunter");
    // intellij stuff power, self, rare, , , , , , 

    public AchievementHunter() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AchievementHunterPower());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}