package thePackmaster.actions.distortionpack;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.distortionpack.DistortionPower;
import thePackmaster.vfx.distortionpack.StaticEffect;

public class StaticAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard card;

    public StaticAction(AbstractPlayer p, AbstractCard c) {
        this.p = p;
        this.card = c;
    }

    @Override
    public void update() {
        this.isDone = true;
        if (p.isDying)
            return;

        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (m == null)
            return;

        this.card.calculateCardDamage(m);
        m.damage(new DamageInfo(p, this.card.damage, this.card.damageTypeForTurn));
        AbstractDungeon.effectList.add(new StaticEffect(m, this.card.damage * 10));

        if (m.lastDamageTaken > 0) {
            ApplyPowerAction instant = new ApplyPowerAction(m, p, new DistortionPower(m, p, m.lastDamageTaken), m.lastDamageTaken);
            ReflectionHacks.setPrivate(instant, ApplyPowerAction.class, "startingDuration", 0.01f);
            ReflectionHacks.setPrivate(instant, AbstractGameAction.class, "duration", 0.01f);
            this.addToTop(new ImproveAction(m, m.lastDamageTaken, instant));
            this.addToTop(instant);
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }
}
