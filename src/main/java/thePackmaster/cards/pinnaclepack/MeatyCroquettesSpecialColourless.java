package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MeatyCroquettesSpecialColourless extends AbstractPinnacleCard {

    public final static String ID = makeID("MeatyCroquettesSpecialColourless");
    private static final int MAGIC = 3;
    private static final int UPGRADE_MAGIC = 3;


    public MeatyCroquettesSpecialColourless() {
        super(ID, 0, CardType.POWER, CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
    }
}
