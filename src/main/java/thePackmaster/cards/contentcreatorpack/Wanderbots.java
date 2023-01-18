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

public class Wanderbots extends AbstractContentCard {
    public final static String ID = makeID("Wanderbots");

    public Wanderbots() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChannelAction(new Wanderbot()));
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