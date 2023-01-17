package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.topDeck;

public class SneakyteakStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("SneakyteakStrike");
    // intellij stuff attack, enemy, uncommon, 8, 2, , , , 

    public SneakyteakStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        tags.add(CardTags.STRIKE);
        cardsToPreview = new DarksideSlap();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        AbstractCard q = new DarksideSlap();
        if (upgraded) q.upgrade();
        topDeck(q);
    }

    public void upp() {
        upgradeDamage(2);
        AbstractCard q = new DarksideSlap();
        q.upgrade();
        cardsToPreview = q;
    }
}