package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Withstand extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Withstand");

    public Withstand() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = this.block = 6;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ReinforcedBodyAction(p, this.block, this.freeToPlayOnce, this.energyOnUse));
    }

    public void upp() {
        upgradeBlock(3);
    }
}