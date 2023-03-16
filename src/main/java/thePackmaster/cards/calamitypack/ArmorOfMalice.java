package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class ArmorOfMalice extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ArmorOfMalice");
    private static final int COST = 1;
    private static final int BLOCK = 3;
    private static final int UPGRADE_BLOCK = 3;

    public ArmorOfMalice() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = BLOCK;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = this.getDebuffCount() + BLOCK + (this.upgraded ? UPGRADE_BLOCK : 0);
        super.applyPowers();
        this.rawDescription = (this.upgraded ? cardStrings.UPGRADE_DESCRIPTION : cardStrings.DESCRIPTION) + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    private int getDebuffCount() {
        return (int)Wiz.getEnemies().stream().flatMap(m -> m.powers.stream()).filter(p -> p.type == AbstractPower.PowerType.DEBUFF).count();
    }
}
