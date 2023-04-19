package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.monsterhunterpack.GremlinLanceFollowupAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GremlinLance extends AbstractMonsterHunterCard {
    public final static String ID = makeID("GremlinLance");

    public static final int DAMAGE = 22;
    public static final int UPG_DAMAGE = 4;
    //public static final int MAGIC = 1;

    public GremlinLance() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        //baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        //addToBot(new GainBlockAction(p, block));
        addToBot(new GremlinLanceFollowupAction(m));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}