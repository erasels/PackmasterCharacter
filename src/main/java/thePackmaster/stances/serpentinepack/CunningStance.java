package thePackmaster.stances.serpentinepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CunningStance extends AbstractStance {
    public static final String STANCE_ID = makeID("Cunning");
    private static long sfxId = -1L;
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceStrings.NAME;
    private static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    private static final float PERCENT = 0.5f;

    public CunningStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    @Override
    public void updateAnimation() {
        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new CunningAura());
        }
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }
        CardCrawlGame.sound.playA("STANCE_ENTER_WRATH", 0.5f);
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.PURPLE, true));
        ArrayList<String> uniquePowers = new ArrayList<>();
        for (AbstractMonster t : Wiz.getEnemies()) {
            for (AbstractPower p : t.powers) {
                if (p.type.equals(AbstractPower.PowerType.DEBUFF) && !uniquePowers.contains(p.ID)){
                    uniquePowers.add(p.ID);
                }
            }
        }
        if (!uniquePowers.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VigorPower(AbstractDungeon.player, uniquePowers.size() * 3), uniquePowers.size() * 3));
        }
        uniquePowers.clear();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0];
    }
}
