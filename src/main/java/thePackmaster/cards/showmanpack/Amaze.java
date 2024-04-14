package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.scene.ShinySparkleEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.effects.showmanpack.MagicianShinySparkle;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Amaze extends AbstractShowmanCard {
    public final static String ID = makeID("Amaze");

    public Amaze() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : Wiz.getEnemies()){
            if (mo.hasPower(WeakPower.POWER_ID)){
                for (int i= 0; i < 20; i++) {
                    float randXOffset = (((float)Math.random() * 200f) - 100f) * Settings.scale;
                    AbstractDungeon.effectsQueue.add(new MagicianShinySparkle(mo.hb.cX + randXOffset, mo.hb.cY + (120f * Settings.scale), mo.hb.cX + randXOffset + (((float)Math.random() * 40f) - 20f), mo.hb.cY + (120f * Settings.scale) + ((float)Math.random() * 40f) - 20f));
                    AbstractDungeon.actionManager.addToBottom(new WaitAction(0.005f));
                }
                CardCrawlGame.sound.playA("NULLIFY_SFX", 0.5F);
                addToBot(new LoseHPAction(mo, p, magicNumber * mo.getPower(WeakPower.POWER_ID).amount));
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}