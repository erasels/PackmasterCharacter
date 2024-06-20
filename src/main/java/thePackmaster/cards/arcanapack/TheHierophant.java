package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TheHierophant extends AbstractAstrologerCard {
    public final static String ID = makeID("TheHierophant");
    // intellij stuff skill, self, rare, , , 6, 3, , 

    public TheHierophant() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = 6;
        exhaust = true;

        AnimatedCardsPatch.loadFrames(this, 7, 0.12f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new RemoveDebuffsAction(p));
    }

    public void upp() {
        upgradeBlock(4);
    }
}