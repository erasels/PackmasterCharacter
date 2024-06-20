package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.utilitypack.HexEffect;

public class GreaterHex extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GreaterHex");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int STATS = 2;

    public GreaterHex() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        this.magicNumber = this.baseMagicNumber = STATS;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new HexEffect(m.hb.cX, m.hb.cY, STATS), 0.6F));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false)));
        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false)));
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber)));
    }
}
