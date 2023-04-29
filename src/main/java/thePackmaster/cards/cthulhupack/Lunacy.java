package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.cthulhupack.MadnessOnceAction;
import thePackmaster.packs.CthulhuPack;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Lunacy extends AbstractCthulhuCard {
    public final static String ID = makeID("Lunacy");

    public Lunacy() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MadnessOnceAction());

        CthulhuPack.lunacyThisCombat++;

        if (!upgraded) {
            Wiz.atb(new LoseHPAction(p,p,1));
        }

        loseSanity(1);
    }

    public void upp() {

    }
}