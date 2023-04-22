package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.cthulhupack.NextTurnGainMadnessPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RlyehFhtagn extends AbstractCthulhuCard {
    public final static String ID = makeID("RlyehFhtagn");

    public RlyehFhtagn() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new NextTurnGainMadnessPower(p, magicNumber));
        this.addToBot(new PressEndTurnButtonAction());
    }

    public void upp() {
        upgradeMagicNumber(5);
    }
}