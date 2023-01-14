package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlockVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("BlockVial");

    public BlockVial() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("BlockPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        blck();
        if (upgraded) Wiz.applyToSelf(new BlurPower(p, 1));
    }


    public void upp() {
    }
}