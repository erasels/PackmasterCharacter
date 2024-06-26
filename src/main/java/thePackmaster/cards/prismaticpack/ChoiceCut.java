package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.prismaticpack.ChoiceCutAction;

public class ChoiceCut extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ChoiceCut");
    private static final int COST = 2;

    public ChoiceCut() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChoiceCutAction(this.upgraded));
    }
}
