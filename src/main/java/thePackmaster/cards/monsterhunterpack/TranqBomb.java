package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.SmokePuffEffect;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import thePackmaster.actions.monsterhunterpack.DamageButDontKill;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TranqBomb extends AbstractMonsterHunterCard {
    public final static String ID = makeID("TranqBomb");

    public static final int DAMAGE = 20;
    public static final int UPG_DAMAGE = 2;
    public static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public TranqBomb() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = UPG_DAMAGE;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.2F));
        addToBot(new WaitAction(0.2f));
        addToBot(new VFXAction(new SmokePuffEffect(m.hb.cX, m.hb.cY)));
        addToBot(new DamageButDontKill(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -magicNumber), -magicNumber));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
    }
}