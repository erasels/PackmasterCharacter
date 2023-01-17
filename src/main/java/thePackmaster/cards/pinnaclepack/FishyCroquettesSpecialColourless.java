package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FishyCroquettesSpecialColourless extends AbstractPinnacleCard {

    public final static String ID = makeID("FishyCroquettesSpecialColourless");
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 2;


    public FishyCroquettesSpecialColourless() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
