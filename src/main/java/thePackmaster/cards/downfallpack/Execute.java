package thePackmaster.cards.downfallpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.GoldenSlashEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Execute extends AbstractDownfallCard {
    public final static String ID = makeID("Execute");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_DAMAGE = 2;

    public Execute() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.player.useJumpAnimation();
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX + 30.0F * Settings.scale, m.hb.cY, true), 0.1F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        atb(new VFXAction(new GoldenSlashEffect(m.hb.cX - 30.0F * Settings.scale, m.hb.cY, true), 0.2F));
        dmg(m, AbstractGameAction.AttackEffect.NONE);

        if (!(AbstractDungeon.player.stance instanceof NeutralStance)) {
            atb(new VFXAction(new GoldenSlashEffect(m.hb.cX, m.hb.cY, true), 0.1F));
            dmg(m, AbstractGameAction.AttackEffect.NONE);
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = !(AbstractDungeon.player.stance instanceof NeutralStance) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}