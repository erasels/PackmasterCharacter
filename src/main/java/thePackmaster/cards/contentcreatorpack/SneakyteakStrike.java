package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SneakyteakStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("SneakyteakStrike");
    // intellij stuff attack, enemy, uncommon, 8, 2, , , , 

    public SneakyteakStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeDamage(2);
    }
}