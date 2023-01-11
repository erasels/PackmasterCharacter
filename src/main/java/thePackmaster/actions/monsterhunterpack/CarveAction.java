//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.actions.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.cards.monsterhunterpack.*;

import java.util.UUID;

public class CarveAction extends AbstractGameAction {
    private DamageInfo info;
    private UUID uuid;
    private AbstractMonster monsterTarget;

    public CarveAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        monsterTarget = (AbstractMonster) target;
        this.duration = 0.1F;
    }

    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            addToBot(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion") && (this.monsterTarget.type == AbstractMonster.EnemyType.BOSS || this.monsterTarget.type == AbstractMonster.EnemyType.ELITE)) {
                addToBot(new VFXAction(new UpgradeShineEffect((float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F)));
                addToBot(new WaitAction(1.6f));
                addToBot(new AddCardToDeckAction(MonsterWeapon(this.target.id)));
            }
        }
        this.tickDuration();
    }

    public AbstractCard MonsterWeapon(String id){
        System.out.println(id);
        switch (id){
            case "GremlinNob":
                return new SkullClub();
            case "Sentry":
                return new CoreBlaster();
            case "Lagavulin":
                return new ShellPauldrons();
            case "TheGuardian":
                return new GuardianShield();
            case "Hexaghost":
                return new InfernoDaggers();
            case "SlimeBoss":
                return new SlimeHammer();
            case "BookOfStabbing":
                return new StabManual();
            case "SlaverBoss":
                return new SlaverWhip();
            case "GremlinLeader":
                return new GremlinLance();
            case "Champ":
                return new ChampMail();
            case "TheCollector":
                return new CursedBow();
            case "BronzeAutomaton":
                return new HyperBlaster();
            case "Nemesis":
                return new EphemeralShroud();
            case "GiantHead":
                return new StoneHelm();
            case "TimeEater":
                return new TimepieceTiara();
            case "AwakenedOne":
                return new AwakenedRitualDagger();
            case "Donu":
                return new DonuAmulet();
            case "Deca":
                return new DecaAmulet();
            case "SpireShield":
                return new SpireShield();
            case "SpireSpear":
                return new SpireSpear();
            default:
                return new Hyperbeam();
        }
    }
}
