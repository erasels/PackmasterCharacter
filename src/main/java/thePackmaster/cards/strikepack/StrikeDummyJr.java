package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.strikepack.StrikeDummyJrPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeDummyJr extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeDummyJr");

    public StrikeDummyJr() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 6;
        exhaust = true;
        baseMagicNumber = magicNumber = 1;

        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.applyToSelf(new StrengthPower(p, magicNumber));
        Wiz.applyToSelf(new StrikeDummyJrPower(p, 2));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.upgradeDamage(3);
    }
}