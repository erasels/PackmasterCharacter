package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.summonerspellspack.CleansingFountainPower;

public class CleansingFountain extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("CleansingFountain");
    private static final int COST = 2;
    private static final int MAGIC = 1;
    private static final int MAGIC2 = 2;
    private static final int UPG_MAGIC2 = 97;


    public CleansingFountain() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = MAGIC;
        secondMagic = baseSecondMagic = MAGIC2;
    }

    @Override
    public void upp() {
        upgradeSecondMagic(UPG_MAGIC2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RemoveDebuffsAction(p));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new CleansingFountainPower(p, secondMagic), secondMagic));
    }
}
