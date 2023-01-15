package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.sneckopack.EnergyCountPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Gulp extends AbstractPackmasterCard {

    public final static String ID = makeID("Gulp");

    public Gulp() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, magicNumber)));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, magicNumber, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.POISON));
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseMagicNumber = baseMagicNumber;
        baseMagicNumber += EnergyCountPatch.energySpentThisCombat;
        magicNumber = baseMagicNumber;
        super.calculateCardDamage(mo);
        baseMagicNumber = realBaseMagicNumber;
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    public void applyPowers() {
        int realBaseMagicNumber = baseMagicNumber;
        baseMagicNumber += EnergyCountPatch.energySpentThisCombat;
        magicNumber = baseMagicNumber;
        super.applyPowers();
        baseMagicNumber = realBaseMagicNumber;
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    public void upp() {
    }
}
