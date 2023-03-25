package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Dark;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Fear extends AbstractOrbCard {
    public final static String ID = makeID("Fear");
    // intellij stuff skill, self, uncommon, , , 13, , 1, 1

    public Fear() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 13;
        baseMagicNumber = magicNumber = 1;

        showEvokeValue = true;
        showEvokeOrbCount = magicNumber;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        showEvokeOrbCount = magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0; i < this.magicNumber; ++i)
            atb(new ChannelAction(new Dark()));
    }

    public void upp() {
        upgradeMagicNumber(1);
        showEvokeOrbCount = magicNumber;
    }
}