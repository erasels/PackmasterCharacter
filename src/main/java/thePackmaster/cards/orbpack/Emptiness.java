package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.entropy.Oblivion;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Emptiness extends AbstractPackmasterCard {
    public final static String ID = makeID("Emptiness");
    // intellij stuff skill, none, common, , , , , 1, 1

    public Emptiness() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DiscardAction(p, p, 1, false));
        for (int i = 0; i < this.magicNumber; ++i)
            atb(new ChannelAction(new Oblivion()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}