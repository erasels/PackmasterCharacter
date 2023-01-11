package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.effects.monsterhunterpack.ScreenOnFireEffectButDoesntCrash;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InfernoDaggers extends AbstractMonsterHunterCard {
    public final static String ID = makeID("InfernoDaggers");

    public static final int DAMAGE = 6;
    public static final int MAGIC = 6;
    public static final int BASECOST = 6;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public InfernoDaggers() {
        super(ID, BASECOST, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ScreenOnFireEffectButDoesntCrash(), 1.0f));
        for (int i = 0; i < magicNumber; i++){
                addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
        addToBot(new WaitAction(1.0f));
        this.cost = 6;
        this.costForTurn = 6;
    }

    public void onRetained() {
            this.updateCost(-1);
    }

    public void upp() {
        this.isInnate = true;
    }
}