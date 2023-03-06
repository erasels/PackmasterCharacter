package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeAllOrbsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.SpireAnniversary5Mod;

public class FrostConversion extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FrostConversion");
    private static final int COST = 3;

    public FrostConversion() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int n = p.filledOrbCount();
                for (int i = 0; i < n; i++) {
                    this.addToTop(new ChannelAction(new Frost()));
                }
                this.addToTop(new EvokeAllOrbsAction());
                this.isDone = true;
            }
        });
    }
}
