package thePackmaster.cards.secretlevelpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.secretlevelpack.AchievementHunterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class AchievementHunter extends AbstractSecretLevelCard {
    public final static String ID = makeID("AchievementHunter");
    // intellij stuff power, self, rare, , , , , , 

    public AchievementHunter() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AchievementHunterPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(-2);
    }
}