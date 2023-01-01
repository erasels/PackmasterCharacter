package thePackmaster.actions.legacypack;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.vfx.legacypack.ShootAnythingEffect;

public class ShootAnythingAction extends AbstractGameAction {

    private ShootAnythingEffect effect;
    private Texture texture;
    private boolean dropOnHead;
    private int count;

    public ShootAnythingAction(AbstractCreature target, Texture texture, boolean dropOnHead) {
        this(target, texture, dropOnHead, 0);
    }

    public ShootAnythingAction(AbstractCreature target, Texture texture, boolean dropOnHead, int count) {
        this.actionType = ActionType.SPECIAL;
        this.target = target;
        this.texture = texture;
        this.dropOnHead = dropOnHead;
        this.count = count;
    }


    public void update() {
        if (effect == null) {
            this.effect = new ShootAnythingEffect(target, texture, dropOnHead, count);
            AbstractDungeon.effectList.add(effect);
        } else if (effect.isDone) {
            this.isDone = true;
        }
    }
}