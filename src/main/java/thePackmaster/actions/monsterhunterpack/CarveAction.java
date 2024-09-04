package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.cards.monsterhunterpack.CarvingKnife;

public class CarveAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractMonster monsterTarget;
    private AbstractCard thisKnife;

    public CarveAction(AbstractCreature target, DamageInfo info, AbstractCard knife) {
        this.info = info;
        setValues(target, info);
        actionType = ActionType.DAMAGE;
        monsterTarget = (AbstractMonster) target;
        startDuration = duration = 0.1F;
        thisKnife = knife;
    }

    public void update() {
        if (duration == startDuration && target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(target.hb.cX, target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            target.damage(info);

            if ((target.isDying || target.currentHealth <= 0) && !target.halfDead && !target.hasPower(MinionPower.POWER_ID)) {
                if ((monsterTarget.type == AbstractMonster.EnemyType.BOSS || monsterTarget.type == AbstractMonster.EnemyType.ELITE) || CarvingKnife.isSlimebossOffspring(target.id)) {
                    addToTop(new QuestCompleteAction(CarvingKnife.getMonsterWeapon(target.id), thisKnife));
                }
            }
        }
        tickDuration();
    }
}
