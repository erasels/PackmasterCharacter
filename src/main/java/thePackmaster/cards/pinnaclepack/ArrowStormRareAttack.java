package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.pinnaclepack.actions.ArrowStormAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ArrowStormRareAttack extends AbstractPinnacleCard {

    public final static String ID = makeID("ArrowStormRareAttack");

    private static final int MAGIC = 3;
    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 4;

    public ArrowStormRareAttack() {
        super(ID, 3, CardType.ATTACK, AbstractCard.CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = this.damage = DAMAGE;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ArrowStormAction(this.magicNumber, this.multiDamage));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

}
