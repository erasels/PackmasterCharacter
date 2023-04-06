package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.ScryCallbackPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Curtain extends AbstractInstadeathCard {
    public final static String ID = makeID("Curtain");

    public Curtain() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.block = this.baseBlock = 3;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(ScryCallbackPatch.scryWithCallback(magicNumber, (cards)->{
            for (int i = 0; i < cards.size(); ++i)
                addToTop(new GainBlockAction(p, p, this.block));
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
