package thePackmaster.actions.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class QuestCompleteAction extends AbstractGameAction {

    AbstractCard cardToObtain;
    AbstractCard cardToRemove;

    public QuestCompleteAction(AbstractCard card, AbstractCard knife) {
        this.cardToObtain = card;
        this.cardToRemove = knife;
        this.duration = 1.5F;
    }

    public void update() {
        AbstractDungeon.effectList.add(new BorderLongFlashEffect(Color.LIGHT_GRAY));
        ShowCardAndObtainEffect s = new ShowCardAndObtainEffect(this.cardToObtain, (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F);
        s.duration = 1.4f;
        s.startingDuration = 1.4f;
        AbstractDungeon.effectList.add(s);
        AbstractDungeon.effectList.add(new UpgradeShineEffect( (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F));
        AbstractCard cToRemove = null;
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.uuid.equals(cardToRemove.uuid)) {
                cToRemove = c;
                break;
            }
        }
        if (cToRemove != null){
            AbstractDungeon.player.masterDeck.removeCard(cToRemove);
        }
        else {
            System.out.println("Knife not found, something went wrong.");
        }

        this.isDone = true;
    }

}
