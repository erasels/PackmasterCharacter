package thePackmaster.cards.warriorpack;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import thePackmaster.cardmodifiers.warriorpack.FrontDamage;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class WorkHammer extends AbstractPackmasterCard {

    public final static String ID = makeID(WorkHammer.class.getSimpleName());

    private static final int COST = 1;

    public WorkHammer(){
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        DamageModifierManager.addModifier(this, new FrontDamage());
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m!=null) {
            AbstractDungeon.topLevelEffectsQueue.add(new UpgradeHammerImprintEffect(m.hb.cX, m.hb.cY));
            if (!Settings.DISABLE_EFFECTS) {
                for (int i = 0; i < 30; ++i) {
                    AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(m.hb.cX + MathUtils.random(-10.0F, 10.0F) * Settings.scale, m.hb.cY + MathUtils.random(-10.0F, 10.0F) * Settings.scale));
                }
            }
        }
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new PlatedArmorPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
