package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.dragonwrathpack.SmiteAction;
import thePackmaster.orbs.dragonwrathpack.LightOrb;
import thePackmaster.powers.dragonwrathpack.PenancePower;
import thePackmaster.powers.dragonwrathpack.confessionpower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RazorWind extends AbstractDragonwrathCard {

    public static final String ID = makeID(RazorWind.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATIO

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public RazorWind(){
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseDamage =DAMAGE;
        this.magicNumber = baseMagicNumber = 2;
        baseSecondMagic = secondMagic = 4;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new confessionpower(p,secondMagic));
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_LIGHTNING_EVOKE"));
        addToBot(new VFXAction(new LightningEffect(p.drawX,p.drawY)));
        addToBot(new DamageAction(p,new DamageInfo(p,magicNumber, DamageInfo.DamageType.THORNS)));
    }

    @Override
    public String cardArtCopy() {
        return Purity.ID;
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeSecondMagic(2);
    }
}
