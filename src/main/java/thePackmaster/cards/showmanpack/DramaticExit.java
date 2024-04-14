package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DramaticExit extends AbstractPackmasterCard {
    public final static String ID = makeID("DramaticExit");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DramaticExit() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseBlock = block = 7;
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