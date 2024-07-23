package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.SpireAnniversary5Mod;

public class SnowMelt extends AbstractShamanCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SnowMelt");
    private static final int COST = 1;
    private static final int DRAW = 2;

    public SnowMelt() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber)));
    }
}
