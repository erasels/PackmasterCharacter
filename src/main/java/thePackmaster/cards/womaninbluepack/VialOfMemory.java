package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class VialOfMemory extends AbstractWomanInBlueCard {
    public final static String ID = makeID("VialOfMemory");


    public VialOfMemory() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("LiquidMemoryPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));

        this.addToBot(new BetterDiscardPileToHandAction(1, 0));
    }


    public void upp() {
        setExhaustive2();
    }
}