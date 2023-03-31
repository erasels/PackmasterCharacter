package thePackmaster.cards.metapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EldritchBlast extends AbstractMetaCard {
    public final static String ID = makeID("EldritchBlast");

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;

    public EldritchBlast() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void triggerOnCardPlayed(AbstractCard c)
    {
        if (c.type == CardType.POWER)
            Wiz.atb(new DiscardToHandAction(this));
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
    }
}
