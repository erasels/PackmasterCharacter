package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StormFront extends AbstractHydrologistCard {
    public final static String ID = makeID("StormFront");

    public StormFront() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.NONE, Subtype.STEAM);
        damage = baseDamage = 30;
        magicNumber = baseMagicNumber = 10;
        retain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, 30);
    }

    @Override
    public void triggerOnManualDiscard() {
        baseDamage += magicNumber;
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}