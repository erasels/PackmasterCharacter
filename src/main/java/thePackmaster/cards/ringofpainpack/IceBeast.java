package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class IceBeast extends AbstractEvolveCard {
    public final static String ID = makeID(IceBeast.class.getSimpleName());

    private static final int DAMAGE = 10;
    private static final int UPGRADE_DAMAGE = 10;
    private static final int BLOCK = 14;
    private static final int UPGRADE_BLOCK = 3;
    private static final int FROST = 1;

    public IceBeast() {
        this(false);
    }

    public IceBeast(boolean isPreviewCard) {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, isPreviewCard);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = FROST;
        setBackgroundTexture(
                "anniv5Resources/images/512/ringofpain/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/ringofpain/" + type.name().toLowerCase() + ".png"
        );
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (this.timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
            atb(new ChannelAction(new Frost()));
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }

    public void triggerOnBlockGain() {
        evolve();
    }

    public void upp() {
        if (timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            upgradeDamage(UPGRADE_DAMAGE);
        } else {
            upgradeBlock(UPGRADE_BLOCK);
        }
    }

    @Override
    protected AbstractCard getPreviewCard() {
        return new IceBeast(true);
    }
}