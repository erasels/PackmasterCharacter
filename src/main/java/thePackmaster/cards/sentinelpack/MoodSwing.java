package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;
import thePackmaster.stances.sentinelpack.Angry;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class MoodSwing extends AbstractSentinelCard {
    public final static String ID = makeID("MoodSwing");

    public MoodSwing() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new DiscardAction(p, p, 2, false));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                if (Wiz.p().stance instanceof Angry){
                    addToTop(new ChangeStanceAction(new CalmStance()));
                } else {
                    addToBot(new ChangeStanceAction(new Angry()));
                }
            }
        });
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


