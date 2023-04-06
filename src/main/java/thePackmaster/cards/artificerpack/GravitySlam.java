package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class GravitySlam extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("GravitySlam");

    public GravitySlam(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 21;
        isMultiDamage = true;
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        for (AbstractCard c : getNeighbors()) {
            addToBot(new ExhaustSpecificCardAction(c,Wiz.hand()));
        }
    }
}
