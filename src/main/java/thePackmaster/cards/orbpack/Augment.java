package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Augment extends AbstractOrbCard {
    public final static String ID = makeID("Augment");
    // intellij stuff skill, none, common, , , , , , 

    public Augment() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                for (AbstractOrb orb : p.orbs) {
                    if (orb instanceof EmptyOrbSlot)
                        return;
                }

                att(new IncreaseMaxOrbAction(1));
            }
        });
    }

    public void upp() {
        this.selfRetain = true;
    }
}