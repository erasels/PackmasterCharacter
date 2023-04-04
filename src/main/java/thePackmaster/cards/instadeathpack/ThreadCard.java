package thePackmaster.cards.instadeathpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.transmutationpack.DrawFilteredCardsAction;
import thePackmaster.vfx.instadeathpack.ExpandCleaveEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThreadCard extends AbstractInstadeathCard {
    public final static String ID = makeID("Thread");

    public ThreadCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        damage = baseDamage = 7;
        isMultiDamage = true;

        this.shuffleBackIntoDrawPile = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //cleave-ish vfx, but transparent, skinner, less intense sound
        addToBot(new VFXAction(new ExpandCleaveEffect(p, new Color(0.2f, 0.1f, 1.0f, 0.5f)), Settings.FAST_MODE ? 0.2f : 0.4f));
        allDmg(AbstractGameAction.AttackEffect.NONE);

        //apply field/cardmod/something to self that modifies where use card action puts this
        //Should be lowest priority, only replacing normal discard.
        addToBot(new DrawFilteredCardsAction(1, (card)->card.type == CardType.SKILL));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
