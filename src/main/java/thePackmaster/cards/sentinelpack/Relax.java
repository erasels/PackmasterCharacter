package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.CalmStance;
import thePackmaster.util.CardArtRoller;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Relax extends AbstractSentinelCard {
    public final static String ID = makeID("Relax");

    public Relax() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new CalmStance()));
    }


    @Override
    public void upp() {
        this.selfRetain = true;
    }


}


