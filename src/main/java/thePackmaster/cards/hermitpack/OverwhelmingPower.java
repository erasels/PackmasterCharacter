package thePackmaster.cards.hermitpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import thePackmaster.powers.hermitpack.OverwhelmingPowerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OverwhelmingPower extends AbstractHermitCard {
    public final static String ID = makeID("OverwhelmingPower");

    public OverwhelmingPower() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Divinity"));
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.RED, true));
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new OverwhelmingPowerPower(p, p, magicNumber), magicNumber));
        this.addToBot(new GainEnergyAction(3));
        this.addToBot(new DrawCardAction(3));
    }

    @Override
    public void upp() {
            upgradeMagicNumber(-2);
    }

    @Override
    public float getTitleFontSize() {
        if(Settings.language== Settings.GameLanguage.ZHS){
            return -1.0F;
        }else {
            return 20.0f;
        }
    }
}
