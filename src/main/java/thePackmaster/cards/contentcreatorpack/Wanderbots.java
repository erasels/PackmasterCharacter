package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.contentcreatorpack.Wanderbot;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Wanderbots extends AbstractPackmasterCard {
    public final static String ID = makeID("Wanderbots");
    // intellij stuff skill, self, uncommon, , , , , , 

    public Wanderbots() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new Wanderbot())); // TODO: Review for consistency purposes
        if (upgraded) atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    if (!(o instanceof EmptyOrbSlot)) {
                        o.onEndOfTurn();
                    }
                }
            }
        });
    }

    public void upp() {
    }
}