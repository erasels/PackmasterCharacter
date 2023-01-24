package thePackmaster.cards.womaninbluepack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlockVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("BlockVial");

    public BlockVial() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 12;
        baseSecondMagic = 6;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("BlockPotion.png"), p.hb.cX, p.hb.cY, p.hb.cX, p.hb.cY, 3F, 0.6F, false, true), 0.6F));
        blck();
        if (upgraded && secondMagic > 0) Wiz.applyToSelf(new NextTurnBlockPower(p, secondMagic));
    }

    @Override
    protected void applyPowersToBlock() {
        super.applyPowersToBlock();
        if (upgraded) {

            secondMagic = block - 6;
            if (secondMagic < 0) secondMagic = 0;
            if (isBlockModified) {
                isSecondMagicModified = true;
            }
        }
    }

    public void upp() {
    }
}