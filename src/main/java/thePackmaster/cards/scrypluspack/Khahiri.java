package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.scryplus.CardSeenScriedInterface;
import thePackmaster.util.Wiz;

import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Khahiri extends AbstractScryPlusCard
        implements CardSeenScriedInterface {

    public final static String ID = makeID(Khahiri.class.getSimpleName());
    public Khahiri() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        setDmg(8);
        setMN(3);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, SLASH_DIAGONAL);
    }

    @Override
    public void onSeenScried() {
        scryFlash();
        baseDamage += magicNumber;
    }
}
