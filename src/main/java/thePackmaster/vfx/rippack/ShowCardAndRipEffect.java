package thePackmaster.vfx.rippack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import thePackmaster.cards.rippack.AbstractRippableCard;
import thePackmaster.cards.rippack.AbstractRippedArtCard;
import thePackmaster.cards.rippack.AbstractRippedTextCard;
import thePackmaster.cards.rippack.SurprisePackArt;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShowCardAndRipEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 0.8F;

    private AbstractRippedArtCard artCard;
    private AbstractRippedTextCard textCard;
    private boolean hasPlayedSound;

    public ShowCardAndRipEffect(AbstractRippableCard card) {
        this.artCard = (AbstractRippedArtCard) card.getRippedParts().get(0).makeStatEquivalentCopy();
        this.artCard.costForTurn = card.costForTurn; //Update cost here for visual accuracy
        this.textCard = (AbstractRippedTextCard) card.getRippedParts().get(1).makeStatEquivalentCopy();
        identifySpawnLocation(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
        this.duration = EFFECT_DUR;

        artCard.drawScale = 0.75F;
        artCard.targetDrawScale = 0.75F;
        artCard.transparency = 0.01F;
        artCard.targetTransparency = 1.0F;
        artCard.fadingOut = false;

        textCard.drawScale = 0.75F;
        textCard.targetDrawScale = 0.75F;
        textCard.transparency = 0.01F;
        textCard.targetTransparency = 1.0F;
        textCard.fadingOut = false;
    }

    private void identifySpawnLocation(float x, float y) {
        artCard.target_y = Settings.HEIGHT * 0.5F;
        textCard.target_y = Settings.HEIGHT * 0.5F;

        artCard.target_x = Settings.WIDTH * 0.5F;
        textCard.target_x = Settings.WIDTH * 0.5F;

        artCard.current_x = artCard.target_x;
        textCard.current_x = artCard.target_x;
        artCard.current_y = artCard.target_y - 200.0F * Settings.scale;
        textCard.current_y = artCard.target_y - 200.0F * Settings.scale;
        AbstractDungeon.effectsQueue.add(new CardPoofEffect(artCard.target_x, artCard.target_y));
        AbstractDungeon.effectsQueue.add(new CardPoofEffect(textCard.target_x, textCard.target_y));
    }

    public void update() {
        if(duration == EFFECT_DUR) {
            CardCrawlGame.sound.play(makeID("RipPack_Rip"));
        }

        if(duration < EFFECT_DUR / 1.5f && !hasPlayedSound && artCard instanceof SurprisePackArt) {
            if(MathUtils.randomBoolean(0.01f)) {
                CardCrawlGame.sound.play(makeID("RipPack_Ohh"));
            } else {
                CardCrawlGame.sound.play(makeID("RipPack_Party"));
            }
            hasPlayedSound = true;
        }
        if(duration < EFFECT_DUR / 2.0F) {

            artCard.target_x = Settings.WIDTH * 0.5F - 200.F * Settings.scale;
            textCard.target_x = Settings.WIDTH * 0.5F + 200.F * Settings.scale;
        }
        duration -= Gdx.graphics.getDeltaTime();
        artCard.update();
        textCard.update();
        if (duration < 0.0F)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        if (!isDone) {
            artCard.render(sb);
            textCard.render(sb);
        }
    }

    public void dispose() {}
}
