package thePackmaster.cards.artificerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.artificerpack.MirrorsCurseModifier;

import java.util.ArrayList;

public class MirrorsCurse extends AbstractArtificerCard {

    public static final String ID = SpireAnniversary5Mod.makeID("MirrorsCurse");
    public ArrayList<AbstractCard> neighbors = null;
    public boolean hasRightNeighbors = false;

    public MirrorsCurse(){
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 7;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.FIRE);
        if (!hasRightNeighbors) neighbors = getNeighbors(); //had to jump through some hoops to give it the right neighbors when played via the modifier
        for (AbstractCard c : neighbors) {
            addToBot(new SimpleAddModifierAction(new MirrorsCurseModifier(this),c, true));
        }
        hasRightNeighbors = false;
    }
}
