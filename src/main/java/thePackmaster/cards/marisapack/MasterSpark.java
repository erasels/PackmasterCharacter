package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MasterSpark extends AbstractMarisaCard implements AmplifyCard {
    public final static String ID = makeID(MasterSpark.class.getSimpleName());
    private static final int DMG = 8, UPG_DMG = 2;
    private static final int MAGIC = 17 - DMG, UPG_MAGIC = 23 - (DMG + UPG_DMG);

    public MasterSpark() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = DMG + MAGIC;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MindblastEffect(p.hb.cX, p.hb.cY, p.flipHorizontal), 0.1F);
        int tmpBaseDamage = baseDamage;
        if (isAmplified(this)) {
            baseDamage = baseMagicNumber;
            calculateCardDamage(null);
        }
        allDmg(AbstractGameAction.AttackEffect.NONE);
        if (isAmplified(this)) {
            baseDamage = tmpBaseDamage;
            applyPowers();
        }
    }

    // lots of jank here because using secondDamage will make the multiDamage array not upgrade
    @Override
    public void applyPowers() {
        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        super.applyPowers();

        magicNumber = damage;
        baseDamage = tmp;

        super.applyPowers();

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = baseDamage;
        baseDamage = baseMagicNumber;

        super.calculateCardDamage(mo);

        magicNumber = damage;
        baseDamage = tmp;

        super.calculateCardDamage(mo);

        isMagicNumberModified = (magicNumber != baseMagicNumber);
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        // stump, logic is in use
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }
}
