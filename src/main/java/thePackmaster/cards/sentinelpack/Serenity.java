package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.stances.sentinelpack.Serene;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Serenity extends AbstractSentinelCard {
    public final static String ID = makeID("Serenity");

    public Serenity() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ChangeStanceAction(new Serene()));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

}


