package thePackmaster.cards.summonspack;

import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_BOX_COLOR;
import static thePackmaster.cards.summonspack.FlavorConstants.FLAVOR_TEXT_COLOR;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.doDmg;

public class SwarmOfBees extends AbstractSummonsCard {
    public final static String ID = makeID(SwarmOfBees.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int DAMAGE = 5;
    private static final int UPGRADED_DAMAGE = 3;

    public SwarmOfBees() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        FlavorText.AbstractCardFlavorFields.boxColor.set(this, FLAVOR_BOX_COLOR);
        FlavorText.AbstractCardFlavorFields.textColor.set(this, FLAVOR_TEXT_COLOR);
        isMultiDamage = true;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doAllDmg(this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, false);
        atb(new ChannelAction(new thePackmaster.orbs.summonspack.SwarmOfBees()));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADED_DAMAGE);
    }
}
