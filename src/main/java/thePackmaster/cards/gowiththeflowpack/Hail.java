package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Hail extends AbstractHydrologistCard {
    public final static String ID = makeID("Hail");

    public Hail() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, Subtype.ICE);
        magicNumber = baseMagicNumber = 1;
        damage = baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int hits = magicNumber;
        if (hits < 1) hits = 1;
        for (int i = 0; i < hits; ++i) {
            hydrologistDamage(p, m, damage);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (magicNumber > 1) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (magicNumber > 1) {
            rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
    }

    @Override
    public void triggerOnManualDiscard() {
        baseMagicNumber++;
        magicNumber++;
    }

    public void upp() {
        upgradeDamage(3);
    }
}