package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class ArmorOfMalice extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ArmorOfMalice");
    private static final int COST = 2;
    private static final int BLOCK_MULTIPLIER = 3;
    private static final int UPGRADE_BLOCK_MULTIPLIER = 1;

    public ArmorOfMalice() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 0;
        this.baseMagicNumber = this.magicNumber = BLOCK_MULTIPLIER;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_BLOCK_MULTIPLIER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.applyPowers();
        this.addToBot(new GainBlockAction(p, this.block));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = this.getDebuffCount() * this.magicNumber;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    private int getDebuffCount() {
        return (int)Wiz.getEnemies().stream().flatMap(m -> m.powers.stream()).filter(p -> p.type == AbstractPower.PowerType.DEBUFF).map(p -> p.ID).distinct().count();
    }
}
