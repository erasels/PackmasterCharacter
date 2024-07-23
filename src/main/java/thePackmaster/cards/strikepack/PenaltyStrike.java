package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PenaltyStrike extends AbstractStrikePackCard {
    public final static String ID = makeID("PenaltyStrike");

    private static final int ATTACK_DMG = 6;
    private static final int STR_LOSS = 4;
    private static final int UPGRADE_PLUS_STR_LOSS = 1;

    public PenaltyStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = ATTACK_DMG;
        baseMagicNumber = magicNumber = STR_LOSS;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_HEAVY);

        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
        if (m != null && !m.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
        }
    }

    public void upp() {
        this.upgradeDamage(3);
        this.upgradeMagicNumber(UPGRADE_PLUS_STR_LOSS);
    }
}