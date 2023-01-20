package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.sneckopack.WhirligigAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Whirligig extends AbstractSneckoCard {

    //changed the numbers slightly (+1 dmg), else it's just a worse Bowling Bash

    public final static String ID = makeID("Whirligig");

    public Whirligig() {
        super(ID, 1, AbstractCard.CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WhirligigAction(this));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
