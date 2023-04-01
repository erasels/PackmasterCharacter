package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class BurningSword extends AbstractFueledCard implements OnConsumedCard {
    public final static String ID = makeID(BurningSword.class.getSimpleName());
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = 2;

    public BurningSword() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void OnConsumed() {
        atb(new DamageAllEnemiesAction(adp(), magicNumber, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}
