package thePackmaster.cards.downfallpack;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.downfallpack.VexVinciblePower;


import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class InvincibleStrength extends AbstractDownfallCard {
    public final static String ID = makeID("InvincibleStrength");

    public InvincibleStrength() {
        super(ID, 1, AbstractCard.CardType.POWER, CardRarity.RARE, AbstractCard.CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VexVinciblePower(p, 1, magicNumber));
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

}

