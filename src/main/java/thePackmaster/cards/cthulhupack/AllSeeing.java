package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import thePackmaster.powers.cthulhupack.AllSeeingPower;
import thePackmaster.powers.cthulhupack.AllSeeingPowerTriggerOnCard;
import thePackmaster.powers.cthulhupack.AllSeeingPowerTriggerOnTurn;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AllSeeing extends AbstractCthulhuCard {
    public final static String ID = makeID("AllSeeing");


    public AllSeeing() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Lunacy();
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AllSeeingPower(p, magicNumber));
        Wiz.applyToSelf(new AllSeeingPowerTriggerOnCard(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}