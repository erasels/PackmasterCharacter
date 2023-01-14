package thePackmaster.cards.shamanpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.shamanpack.FreezeAndBurnPower;

public class FreezeAndBurn extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FreezeAndBurn");
    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE = 1;
    private static final int TEMP_STRENGTH_DOWN = 2;
    private static final int UPGRADE_TEMP_STRENGTH_DOWN = 1;

    public boolean nextTurnEffect = false;

    public FreezeAndBurn() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = TEMP_STRENGTH_DOWN;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
        this.upgradeMagicNumber(UPGRADE_TEMP_STRENGTH_DOWN);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        if (this.nextTurnEffect) {
            this.addToBot(new SFXAction("ATTACK_HEAVY"));
            this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        }
        else {
            this.addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), Settings.FAST_MODE ? 0.3F : 1.5F));
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
                if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
                    this.addToBot(new ApplyPowerAction(m, p, new GainStrengthPower(m, this.magicNumber), this.magicNumber));
                }
            }
            this.addToBot(new ApplyPowerAction(p, p, new FreezeAndBurnPower(p, this)));
        }
    }
}
