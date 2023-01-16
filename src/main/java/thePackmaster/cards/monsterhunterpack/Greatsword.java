package thePackmaster.cards.monsterhunterpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityStanceChangeParticle;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Greatsword extends AbstractMonsterHunterCard {
    public final static String ID = makeID("Greatsword");

    public static final int DAMAGE = 28;
    public static final int UPG_DAMAGE = 8;
    public static final int MAGIC = 8;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    public int originalDamage;

    public Greatsword() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        originalDamage = baseDamage;
        baseMagicNumber = magicNumber = UPG_DAMAGE;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.8F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.baseDamage = damage = originalDamage;
        if (upgraded){
            upgradeDamage(UPG_DAMAGE);
        }
        initializeDescription();
    }

    public void onRetained() {
        if (EnergyPanel.totalCount > 0){
         for (int i = 0; i<EnergyPanel.totalCount; i++){
             this.upgradeDamage(this.magicNumber);
         }
         for (int i = 0; i < EnergyPanel.totalCount * 10; i++){
             CardCrawlGame.sound.playAV("STANCE_ENTER_DIVINITY", 1.0f, 0.2f);
             addToBot(new VFXAction(new DivinityStanceChangeParticle(Color.RED, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY)));
         }
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}