package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.entropy.Oblivion;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class RuinousPortent extends AbstractPackmasterCard {
    public final static String ID = makeID("RuinousPortent");
    // intellij stuff skill, none, uncommon, , , , , 0, 1

    public RuinousPortent() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this,
                (effect, params)->{
                    effect += params[0];
                    for(int i = 0; i < effect; ++i) {
                        att(new ChannelAction(new Oblivion()));
                    }
                    return true;
                }, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}