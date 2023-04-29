package thePackmaster.orbs.contentcreatorpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makeImagePath;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Wanderbot extends AbstractOrb {
    private static final OrbStrings orbString;
    private static final Texture myTex = TexLoader.getTexture(makeImagePath("/orbs/wanderbot_orb.png"));

    public Wanderbot() {
        this.ID = makeID("Wanderbot");
        this.img = myTex;
        this.name = orbString.NAME;
        this.basePassiveAmount = 4;
        this.passiveAmount = this.basePassiveAmount;
        this.baseEvokeAmount = this.evokeAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
        scale = 1.5F;
    }

    public void updateDescription() {
        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
    }

    public void onEvoke() {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractMonster target = Wiz.getFrontmostEnemy();
                if (target != null) {
                    att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, Wanderbot.applyLockOn(target, evokeAmount), DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    att(new VFXAction(new SmallLaserEffect(target.hb.cX, target.hb.cY, hb.cX, hb.cY), 0.1F));
                    att(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
                }
                isDone = true;
            }
        });
        atb(new DrawCardAction(1));
    }

    public void onEndOfTurn() {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractMonster target = Wiz.getFrontmostEnemy();
                if (target != null) {
                    att(new DamageAction(target, new DamageInfo(AbstractDungeon.player, Wanderbot.applyLockOn(target, evokeAmount), DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
                    att(new VFXAction(new SmallLaserEffect(target.hb.cX, target.hb.cY, hb.cX, hb.cY), 0.1F));
                    att(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
                }
                isDone = true;
            }
        });
    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.1F);
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
    }

    public void playChannelSFX() {
        CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.3F);
    }

    public AbstractOrb makeCopy() {
        return new Wanderbot();
    }


    @Override
    public void render(SpriteBatch sb) {

        this.shineColor.a = this.c.a / 2.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, this.scale * 1.2F, this.angle, 0, 0, 96, 96, false, false);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
        renderText(sb);
        hb.render(sb);
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("Wanderbot");
    }
}
