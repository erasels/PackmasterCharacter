package thePackmaster.cards.dragonwrathpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.GainCustomBlockAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.dragonwrathpack.SmiteAction;
import thePackmaster.orbs.dragonwrathpack.LightOrb;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SearingLight extends AbstractDragonwrathCard {

    public static final String ID = makeID(SearingLight.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATIO

    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public SearingLight(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseBlock = 20;
        this.magicNumber = baseMagicNumber = 5;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
        addToBot(new VFXAction(new LightningEffect(p.drawX,p.drawY)));
        addToBot(new DamageCallbackAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE, integer -> {
            addToBot(new GainBlockAction(AbstractDungeon.player,block));
        }));
        addToBot(new ChannelAction(new LightOrb()));
    }


    // Upgraded stats.
    @Override
    public void upp() {
        upgradeMagicNumber(-2);
        upgradeDamage(UPGRADE_PLUS_DMG);
    }
}

