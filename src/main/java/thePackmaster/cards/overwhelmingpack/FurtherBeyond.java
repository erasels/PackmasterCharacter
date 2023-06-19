package thePackmaster.cards.overwhelmingpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningOrbActivateEffect;
import thePackmaster.patches.overwhelmingpack.ZOOMAnimationPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FurtherBeyond extends AbstractOverwhelmingCard {
    public final static String ID = makeID("FurtherBeyond");

    public FurtherBeyond() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);

        this.damage = this.baseDamage = 40;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!super.canUse(p, m))
            return false;

        if (EnergyPanel.getCurrentEnergy() == 0) {
            return true;
        }
        else {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ReflectionHacks.setPrivate(p, AbstractCreature.class, "animationTimer", 0f);

        this.addToBot(new VFXAction(new InflameEffect(p), Settings.FAST_MODE ? 0.4F : 0.6F));
        float minX = p.hb.cX - (p.hb.width / 3f), maxX = p.hb.cX + (p.hb.width / 3f);
        float minY = p.hb.y, maxY = minY + (p.hb.height * 0.8f);
        int sparkle = MathUtils.random(6, 11);
        this.addToBot(new VFXAction(new AbstractGameEffect() {
            @Override
            public void update() {
                for (int i = 0; i < sparkle; ++i) {
                    AbstractDungeon.effectsQueue.add(new LightningOrbActivateEffect(MathUtils.random(minX, maxX), MathUtils.random(minY, maxY)));
                }
                this.isDone = true;
            }

            @Override
            public void render(SpriteBatch spriteBatch) {
            }

            @Override
            public void dispose() { }
        }, 0.1f));

        int zap = MathUtils.random(3, 5);
        boolean sound = true;
        for (int i = 0; i < zap; ++i) {
            float offset = MathUtils.random(p.hb.width / 4, p.hb.width) * MathUtils.randomSign();
            addToBot(new VFXAction(new LightningEffect(p.drawX + offset, p.drawY), MathUtils.random(0, Settings.FAST_MODE ? 0.05f: 0.1f)));
            if (sound) {
                addToBot(new SFXAction("ORB_LIGHTNING_EVOKE"));
            }
            sound = !sound;
        }

        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ZOOMAnimationPatch.useZOOMAnimation(p);
            }
        });

        addToBot(new SFXAction("ATTACK_DAGGER_4", 0.3f, true));
        addToBot(new WaitAction(0.1f));
        addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, p.hb.y + p.hb.height * 0.33f)));
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
    }

    public void upp() {
        upgradeDamage(10);
    }
}