package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.legacypack.SlimeSplashEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SlimeSpray extends AbstractPackmasterCard {
    public final static String ID = makeID("SlimeSpray");

    private static final int ATTACK_DMG = 7;
    private static final int UPGRADE_PLUS_DMG = 4;


    public SlimeSpray() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        this.baseDamage = ATTACK_DMG;
        this.isMultiDamage = true;
        selfRetain = true;

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        AbstractDungeon.actionManager.addToBottom(
                new SFXAction("MONSTER_SLIME_ATTACK", 0.4F));

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDead && !mo.escaped) {
                AbstractDungeon.actionManager.addToBottom(
                        new ApplyPowerAction(mo, p, new SlowPower(mo, 0), 0, true, AbstractGameAction.AttackEffect.NONE));

                AbstractDungeon.actionManager.addToBottom(
                        new VFXAction(new SlimeSplashEffect(mo.drawX, mo.drawY + mo.hb_h/2.0F)));
            }
        }
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DMG);
    }
}