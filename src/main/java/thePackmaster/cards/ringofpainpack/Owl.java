package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.ringofpainpack.OwlAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class Owl extends AbstractEvolveCard {
    public final static String ID = makeID(Owl.class.getSimpleName());

    private static final int DAMAGE = 24;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int VULNERABLE = 2;

    public Owl() {
        this(false);
    }

    public Owl(boolean isPreviewCard) {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, isPreviewCard);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = VULNERABLE;

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/ringofpain/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/ringofpain/" + type.name().toLowerCase() + ".png"
        );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            atb(new OwlAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        } else {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        }
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));

    }

    public void triggerEvolveOnExhaust() {
        evolve();
    }

    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    protected AbstractCard getPreviewCard() {
        return new Owl(true);
    }
}