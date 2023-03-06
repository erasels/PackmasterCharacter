package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Plunge extends AbstractFrostCard {
    public final static String ID = makeID("Plunge");

    public Plunge() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractOrb o = new Frost();
        atb(new ChannelAction(o));

        atb(new WaitAction(0.1F));
        atb(new WaitAction(0.1F));

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (p.orbs.contains(o)){
                    o.onEvoke();
                }
            }
        });
        atb(new WaitAction(0.1F));

        if (upgraded)
            atb(new ChannelAction(new Frost()));
    }

    public void upp() {

    }
}