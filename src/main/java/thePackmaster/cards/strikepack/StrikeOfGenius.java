package thePackmaster.cards.strikepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.strikepack.StrikeOfGeniusPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikeOfGenius extends AbstractStrikePackCard {
    public final static String ID = makeID("StrikeOfGenius");

    public StrikeOfGenius() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.FIRE);

        Wiz.applyToSelf(new StrikeOfGeniusPower(p, 1));
    }


    public void upp() {
        upgradeDamage(3);
    }
}