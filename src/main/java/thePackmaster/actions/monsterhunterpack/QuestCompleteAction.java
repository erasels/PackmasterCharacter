package thePackmaster.actions.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;

public class QuestCompleteAction extends AbstractGameAction {

    AbstractCard cardToObtain;
    AbstractCard cardToRemove;

    public QuestCompleteAction(AbstractCard card, AbstractCard knife) {
        cardToObtain = card;
        cardToRemove = knife;
        duration = 1.5F;
    }

    public void update() {
        AbstractDungeon.effectList.add(new BorderLongFlashEffect(Color.LIGHT_GRAY));
        ShowCardAndObtainEffect obtainEffect = new ShowCardAndObtainEffect(cardToObtain, (float) Settings.WIDTH * 3.0f / 4.0F, (float) Settings.HEIGHT / 2.0F);
        obtainEffect.duration = 1.4f;
        obtainEffect.startingDuration = 1.4f;
        AbstractDungeon.effectList.add(obtainEffect);
        AbstractDungeon.effectList.add(new UpgradeShineEffect((float) Settings.WIDTH * 3.0f / 4.0F, (float) Settings.HEIGHT / 2.0F));
        cardToRemove = StSLib.getMasterDeckEquivalent(cardToRemove);

        if (cardToRemove != null) {
            AbstractDungeon.player.masterDeck.removeCard(cardToRemove);
        } else {
            SpireAnniversary5Mod.logger.warn("Knife not found, something went wrong.");
        }

        isDone = true;
    }

}
