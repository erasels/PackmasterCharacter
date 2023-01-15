package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Allocate extends AbstractPackmasterCard {
    public final static String ID = makeID("Allocate");
    // intellij stuff power, none, uncommon, , , , , , 

    public Allocate() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = 0;
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped())
                ++amt;
        }
        if (amt > 0)
            atb(new IncreaseMaxOrbAction(amt));
    }

    public void upp() {
        isInnate = true;
    }
}