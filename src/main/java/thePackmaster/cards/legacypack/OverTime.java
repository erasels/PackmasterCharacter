package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OverTime extends AbstractLegacyCard {
    public final static String ID = makeID("OverTime");

    private static final int ATTACK_PER_CARD = 4;
    private static final int UPGRADE_PLUS_DMG = 3;

    public OverTime() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.baseMagicNumber = ATTACK_PER_CARD;
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage > 0){
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    @Override
    public void applyPowers() {
        int tmp = baseDamage;
        baseDamage += Wiz.p().exhaustPile.size() * magicNumber;
        super.applyPowers();
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = baseDamage;
        baseDamage += Wiz.p().exhaustPile.size() * magicNumber;
        super.calculateCardDamage(mo);
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_DMG);
    }
}