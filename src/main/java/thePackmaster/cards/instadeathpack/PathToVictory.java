package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.powers.instadeathpack.Precision;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PathToVictory extends AbstractInstadeathCard {
    public final static String ID = makeID("PathToVictory");

    public PathToVictory() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);

        this.baseMagicNumber = this.magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int vigor = magicNumber;
        addToBot(new HandSelectAction(69420, (c)->true, (cards)->{
            int amt = cards.size() * vigor;
            for (AbstractCard c : cards)
                p.hand.moveToDeck(c, false);
            if (amt > 0) {
                addToBot(new ApplyPowerAction(p, p, new Precision(p, amt), amt));
            }
        }, null, PutOnDeckAction.TEXT[0], false, true, true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
