package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class RopeTrick extends AbstractCosmosCard {
    public final static String ID = makeID("RopeTrick");

    public RopeTrick() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 4;
        block = baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new DistortionPower(m, p, magicNumber));
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}