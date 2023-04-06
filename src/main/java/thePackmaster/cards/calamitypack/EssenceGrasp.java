package thePackmaster.cards.calamitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.calamitypack.EssenceGraspEffect;

public class EssenceGrasp extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("EssenceGrasp");
    private static final int COST = 1;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 3;

    public EssenceGrasp() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
    }

    @Override
    public void upp() {
        this.upgradeDamage(UPGRADE_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new EssenceGraspEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.1F));
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToTop(new DrawCardAction(getDraw(m)));
                this.isDone = true;
            }
        });
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        super.calculateCardDamage(m);
        int draw = this.baseMagicNumber = this.magicNumber = this.getDraw(m);
        this.rawDescription = cardStrings.DESCRIPTION + (draw == 1 ? cardStrings.EXTENDED_DESCRIPTION[0] : cardStrings.EXTENDED_DESCRIPTION[1]);
        this.initializeDescription();
    }

    private int getDraw(AbstractMonster m) {
        boolean hasIgnite = m.hasPower(IgnitePower.POWER_ID);
        boolean hasFrostbite = m.hasPower(FrostbitePower.POWER_ID);
        boolean hasPoison = m.hasPower(PoisonPower.POWER_ID);
        return (hasIgnite ? 1 : 0) + (hasFrostbite ? 1 : 0) + (hasPoison ? 1 : 0);
    }

}
