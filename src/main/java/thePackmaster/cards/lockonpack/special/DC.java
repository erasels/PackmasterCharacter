package thePackmaster.cards.lockonpack.special;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cards.lockonpack.AbstractLockonCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DC extends AbstractLockonCard {

    public final static String ID = makeID(DC.class.getSimpleName());

    public DC() {
        // cardID, cost, type, rarity, target,  color)
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        selfRetain = true;
        exhaust = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Lightning orb = new Lightning();
        addToBot(new ChannelAction(orb));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!Wiz.adp().orbs.contains(orb)) return;
                if (upgraded) orb.onEndOfTurn();
                orb.onEvoke();
            }
        });
    }
}
