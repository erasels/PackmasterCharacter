package thePackmaster.actions.summonspack;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;

public class JinxLoseHPAction extends AbstractGameAction {
    private static final float DURATION = 0.15F;
    public static final Color JINX_COLOR = new Color(0, 0, 204f/255, 1f);
    private final boolean quiet;

    public JinxLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, boolean quiet) {
        setValues(target, source, amount);
        actionType = ActionType.DAMAGE;
        startDuration = duration = DURATION;
        this.quiet = quiet;
    }

    public void update() {
        if (duration == DURATION && target.currentHealth > 0 && !quiet)
            CardCrawlGame.sound.playAV(SpireAnniversary5Mod.EVIL_KEY, 0.1f, 0.5f);
        tickDuration();
        if (isDone) {
            if (target.currentHealth > 0) {
                DamageInfo info = new DamageInfo(source, amount, DamageType.HP_LOSS);
                ColoredDamagePatch.DamageActionColorField.damageColor.set(this, JINX_COLOR);
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.SLOW);
                target.damage(info);
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                AbstractDungeon.actionManager.clearPostCombatActions();

            if (!Settings.FAST_MODE)
                addToTop(new WaitAction(0.1F));
        }
    }
}
