package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class Slime extends AbstractEvolveCard {
    public final static String ID = makeID(Slime.class.getSimpleName());

    private static final int POISON = 4;
    private static final int UPGRADE_POISON = 1;

    public Slime() {
        this(false);
    }

    public Slime(boolean isPreviewCard) {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY, isPreviewCard);
        magicNumber = baseMagicNumber = POISON;

        
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
        if (this.timesUpgraded >= AbstractEvolveCard.MAX_UPGRADES) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractPower poison = m.getPower(PoisonPower.POWER_ID);
                    if (poison != null) {
                        atb(new DamageAction(m, new DamageInfo(p, poison.amount, DamageInfo.DamageType.HP_LOSS), AttackEffect.POISON));
                    }
                    this.isDone = true;
                }
            });
        }
    }

    public void triggerOnDebuff() {
        evolve();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_POISON);
    }

    @Override
    protected AbstractCard getPreviewCard() {
        return new Slime(true);
    }
}