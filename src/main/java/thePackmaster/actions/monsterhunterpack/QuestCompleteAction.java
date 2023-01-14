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

    public QuestCompleteAction(AbstractCard card) {
        this.cardToObtain = card;
        this.duration = 1.5F;
    }

    public void update() {
        AbstractDungeon.effectList.add(new BorderLongFlashEffect(Color.LIGHT_GRAY));
        ShowCardAndObtainEffect s = new ShowCardAndObtainEffect(this.cardToObtain, (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F);
        s.duration = 2.0f;
        s.startingDuration = 1.4f;
        AbstractDungeon.effectList.add(s);
        AbstractDungeon.effectList.add(new UpgradeShineEffect( (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F));
        this.isDone = true;
    }

}
