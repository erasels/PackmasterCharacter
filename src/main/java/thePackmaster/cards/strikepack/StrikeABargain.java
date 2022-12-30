package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeABargain extends AbstractPackmasterCard {
    public final static String ID = makeID("StrikeABargain");

    public StrikeABargain() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO - Uhoh.  How do dis.. Bullet Time is hardcoded shenanigans.
        //"Your attacks costs 0 this turn. You cannot play non-Attacks."
    }

    public void upp() {
        this.upgradeBaseCost(2);
    }
}