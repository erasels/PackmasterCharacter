package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.showmanpack.ShowstopperAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Showstopper extends AbstractShowmanCard {
    public final static String ID = makeID("Showstopper");
    private final static int BLOCKTHRESHOLD = 100;
    private final static int DAMAGE = 50;

    public Showstopper() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
        baseDamage = damage = DAMAGE;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int energy = this.energyOnUse;
        if (upgraded){
            energy++;
        }
        addToBot(new ShowstopperAction(p, magicNumber, this.freeToPlayOnce, energy, BLOCKTHRESHOLD, damage));
    }

    public void upp() {
    }
}