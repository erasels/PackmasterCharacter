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
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChannelAction(new Frost()));
    }

    public void upp() {
        upgradeBlock(3);
    }
}