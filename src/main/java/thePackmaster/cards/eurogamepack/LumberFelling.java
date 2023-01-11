package thePackmaster.cards.eurogamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LumberFelling extends AbstractVPCard{
    public static final String ID = makeID("LumberFelling");

    public LumberFelling() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseDamage = this.damage = 4;
        this.baseSecondMagic = secondMagic = 10;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < this.magicNumber; ++i) {
            atb(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
        this.applyToSelf(new VictoryPoints(AbstractDungeon.player, this.secondMagic));
    }
    public void upp() {
            this.upgradeMagicNumber(1);
    }
}
