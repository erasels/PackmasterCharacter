package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
        magicNumber = baseMagicNumber = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        magicNumber = this.baseMagicNumber = hitAmount(3)+1;

        for (int a = 0; a < this.magicNumber; a++)
        Wiz.atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    public int hitAmount(int hits)
    {
        int a = 0;

        for(int i = 0; i < Math.min(hits, AbstractDungeon.player.drawPile.size()); ++i) {
            if (Wiz.p().drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1).type == CardType.ATTACK)
            a++;
        }

        return a;
    }

    public void applyPowers() {
        magicNumber = baseMagicNumber = hitAmount(3);

        super.applyPowers();

        rawDescription = cardStrings.DESCRIPTION + (magicNumber == 1 ? cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.EXTENDED_DESCRIPTION[1]);
        initializeDescription();
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
    }
}
