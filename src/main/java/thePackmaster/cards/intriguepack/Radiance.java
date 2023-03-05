package thePackmaster.cards.intriguepack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Radiance extends AbstractIntrigueCard {
    public final static String ID = makeID("Radiance");

    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 5;

    public Radiance() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (this.rarity == CardRarity.RARE) {
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.YELLOW, true));
            this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
            for(AbstractMonster mo : Wiz.getEnemies()) {
                this.addToBot(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                demote(Radiance.this);
                isDone = true;
            }
        });
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(1);
    }
}