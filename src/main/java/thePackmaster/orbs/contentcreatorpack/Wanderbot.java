package thePackmaster.orbs.contentcreatorpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.cards.highenergypack.StruckByATrain;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;

import static thePackmaster.util.Wiz.atb;

public class Wanderbot extends AbstractOrb {
    private static final OrbStrings orbString;
    private static final Texture myTex = TexLoader.getTexture("anniv5Resources/images/orbs/summonsPack/Wolf.png"); //TODO: Real image

    public Wanderbot() {
        this.ID = "Wanderbot";
        this.img = ImageMaster.ORB_LIGHTNING;
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
        AbstractMonster target = StruckByATrain.getFrontmostEnemy();
        atb(new DamageAction(target, new DamageInfo(AbstractDungeon.player, evokeAmount, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        atb(new DrawCardAction(1));
    }

    public void onEndOfTurn() {
        AbstractMonster target = StruckByATrain.getFrontmostEnemy();
        atb(new DamageAction(target, new DamageInfo(AbstractDungeon.player, passiveAmount, DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.1F);
    }

    public void playChannelSFX() {
        if (MathUtils.randomBoolean()) {
            CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.3F);
        } else {
            CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.3F);
        }
    }

    public AbstractOrb makeCopy() {
        return new Wanderbot();
    }

    @Override
    public void render(SpriteBatch sb) { // TODO: Render better, I'm not familiar with orbs
        sb.setColor(Color.WHITE.cpy());
        ImageHelper.drawTextureScaled(sb, myTex, cX, cY);
        renderText(sb);
        hb.render(sb);
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("Wanderbot");
    }
}
