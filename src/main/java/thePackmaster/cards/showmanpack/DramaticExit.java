package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DramaticExit extends AbstractShowmanCard {
    public final static String ID = makeID("DramaticExit");

    public DramaticExit() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 6;
        exhaust = true;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}