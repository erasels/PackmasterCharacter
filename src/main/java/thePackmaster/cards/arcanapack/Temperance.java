package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Temperance extends AbstractAstrologerCard {
    public final static String ID = makeID("Temperance");
    // intellij stuff skill, none, uncommon, , , , , , 

    public Temperance() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;

        AnimatedCardsPatch.loadFrames(this, 37, 0.12f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.exhaustPile.isEmpty())
            atb(new DrawCardAction(p.exhaustPile.size()));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}