package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;

public class CobrasGift extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("CobrasGift");
    private static final int COST = 0;
    private static final int POISON = 2;

    public CobrasGift() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = POISON;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber)));
        this.addToBot(new GainEnergyAction(1));
    }
}
