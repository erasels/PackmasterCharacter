package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.pinnaclepack.actions.ArrowStormAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ArrowStormRareAttack extends AbstractPinnacleCard {

    public final static String ID = makeID("ArrowStormRareAttack");

    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;
    private static final int DAMAGE = 15;
    private static final int UPGRADE_DAMAGE = 1;



    public ArrowStormRareAttack() {
        super(ID, 3, CardType.ATTACK, AbstractCard.CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = this.damage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ArrowStormAction(this.magicNumber, this.damage));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeDamage(UPGRADE_DAMAGE);
    }

}
