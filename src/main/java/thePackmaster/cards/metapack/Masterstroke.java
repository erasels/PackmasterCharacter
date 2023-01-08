package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Masterstroke extends AbstractPackmasterCard {
    public final static String ID = makeID("Masterstroke");

    private static final int DAMAGE = 4;
    private static final int BLOCK = 4;

    public Masterstroke() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void applyPowersToBlock()
    {
        this.block = baseBlock;

        AbstractPower dex = AbstractDungeon.player.getPower(DexterityPower.POWER_ID);
        if (dex != null) {
            dex.amount *= this.magicNumber;
        }

        super.applyPowersToBlock();

        if (dex != null) {
            dex.amount /= this.magicNumber;
        }
    }

    public void applyPowers() {
        // Ahahahahahaha. Hahahahahaha. Ahahahahahahahahaha.
        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        if (strength != null) {
            strength.amount *= this.magicNumber;
        }

        super.applyPowers();

        if (strength != null) {
            strength.amount /= this.magicNumber;
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower strength = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        if (strength != null) {
            strength.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);

        if (strength != null) {
            strength.amount /= this.magicNumber;
        }
    }

    public void upp() {
            upgradeMagicNumber(1);
    }
}
