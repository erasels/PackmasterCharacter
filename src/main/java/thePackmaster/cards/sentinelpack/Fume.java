package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.sentinelpack.Angry;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Fume extends AbstractSentinelCard {
    public final static String ID = makeID("Fume");

    public Fume() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new Angry()));
    }


    @Override
    public void upp() {
        this.selfRetain = true;
    }

}


