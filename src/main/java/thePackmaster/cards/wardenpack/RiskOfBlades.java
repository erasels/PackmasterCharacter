package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RiskOfBlades extends AbstractWardenCard {
    public final static String ID = makeID("RiskOfBlades");

    private static final int DAMAGE = 6;
    private static final int DAMAGE_UPGRADE = 2;

    public RiskOfBlades() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int hitnum = 1;

        if (!Wiz.drawPile().isEmpty() && (Wiz.drawPile().getTopCard().type == CardType.ATTACK))
            hitnum = 3;

        for (int a = 0; a < hitnum; a++)
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = ((!Wiz.drawPile().isEmpty() && (Wiz.drawPile().getTopCard().type == CardType.ATTACK)) ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR);
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
    }
}
