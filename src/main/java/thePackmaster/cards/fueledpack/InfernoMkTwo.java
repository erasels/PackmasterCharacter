package thePackmaster.cards.fueledpack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.fueledpack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.*;

public class InfernoMkTwo extends AbstractFueledCard {
    public final static String ID = makeID(InfernoMkTwo.class.getSimpleName());
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int COST = 2;

    private static final int DAMAGE = 12;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;

    public InfernoMkTwo() {
        super(ID, COST, TYPE, RARITY, TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageAllEnemiesAction action = new DamageAllEnemiesAction(adp(), multiDamage, damageTypeForTurn,
                AbstractGameAction.AttackEffect.FIRE);
        forAllMonstersLiving(mo -> applyToEnemy(mo, new IgnitePower(mo, magicNumber)));
        atb(new ConsumeToDoAction(action));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
        upMagic(UPGRADE_MAGIC);
    }
}
