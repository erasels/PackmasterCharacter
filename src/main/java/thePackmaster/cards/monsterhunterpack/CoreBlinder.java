package thePackmaster.cards.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.FastShakeAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CoreBlinder extends AbstractPackmasterCard {
    public final static String ID = makeID("CoreBlinder");

    public static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;
    AbstractCard prev;
    boolean previewCard;

    public CoreBlinder() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        prev = new CoreBlaster(true);
        this.cardsToPreview = prev;
        this.exhaust = true;
        previewCard = false;
    }

    public CoreBlinder(boolean generated) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        if (!generated) {
            prev = new CoreBlaster(true);
            this.cardsToPreview = prev;
        }
        previewCard = generated;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP"));
        if (!Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.ROYAL, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.5F));
            AbstractDungeon.actionManager.addToBottom(new FastShakeAction(AbstractDungeon.player, 0.6F, 0.2F));
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShockWaveEffect(this.hb.cX, this.hb.cY, Color.ROYAL, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.1F));
            AbstractDungeon.actionManager.addToBottom(new FastShakeAction(AbstractDungeon.player, 0.6F, 0.15F));
        }
        if (upgraded){
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters){
                if (!mo.halfDead && !mo.isDead && !mo.isDying){
                    addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false), magicNumber));
                    addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber));
                }
            }
        }
        else {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
        }
        AbstractCard gen = new CoreBlaster(false);
        if (upgraded) {gen.upgrade();}
        addToBot(new MakeTempCardInDrawPileAction(gen, 1, true, false, false));
    }

    public void upp() {
        this.target = CardTarget.ALL_ENEMY;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        if (!previewCard) {
            prev.upgrade();
        }
        initializeDescription();
    }
}