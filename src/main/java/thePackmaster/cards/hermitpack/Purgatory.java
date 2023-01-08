package thePackmaster.cards.hermitpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.vfx.hermitpack.ShortScreenFire;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Purgatory extends AbstractHermitCard {
    public final static String ID = makeID("Purgatory");

    private static final int DAMAGE = 24;
    private static final int DAMAGE_UP = 6;

    public Purgatory() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        this.isEthereal = true;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new ShortScreenFire(), 0.5F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upp() {
        upgradeDamage(DAMAGE_UP);
    }
}