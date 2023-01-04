package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.strikepack.StrikeABargainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeABargain extends AbstractPackmasterCard {
    public final static String ID = makeID("StrikeABargain");

    public StrikeABargain() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrikeABargainPower(p));
    }

    public void upp() {
        this.upgradeBaseCost(2);
    }
}