package thePackmaster.cards.downfallpack;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class SlimeCrush extends AbstractDownfallCard {
    public final static String ID = makeID("SlimeCrush");


    private static final int DAMAGE = 20;
    private static final int UPGRADE_DAMAGE = 5;

    public SlimeCrush() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);

        baseDamage = DAMAGE;
        exhaust = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new AnimateJumpAction(p));
        if (!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(0.1F, 1.0F, 0.1F, 0.0F))));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        } else {
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters)
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(mon.hb.cX, mon.hb.cY, new Color(0.1F, 1.0F, 0.1F, 0.0F))));
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }


}
