package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.dimensiongatepack.AbstractDimensionalCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ThrowWrench extends AbstractDimensionalCard {
    public final static String ID = makeID("ThrowWrench");

    public ThrowWrench() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 7;
        this.isMultiDamage = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (AbstractCard c:AbstractDungeon.player.hand.group) {
            if (c.hasTag(SpireAnniversary5Mod.ISCARDMODIFIED)){
                allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
                return;
            }
        }

    }

    public void upp() {
        upgradeDamage(3);
    }
}