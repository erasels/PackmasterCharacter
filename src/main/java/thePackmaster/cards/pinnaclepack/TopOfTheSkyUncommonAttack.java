package thePackmaster.cards.pinnaclepack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TopOfTheSkyUncommonAttack extends AbstractPinnacleCard {

    public final static String ID = makeID("TopOfTheSkyUncommonAttack");
    private static final int ATTACK = 2;
    private static final int BLOCK = 2;
    private static final int EXHAUSTIVE = 2;
    private static final int UPGRADE_EXHAUSTIVE = 1;

    public TopOfTheSkyUncommonAttack() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.selfRetain = true;
        this.baseBlock = BLOCK;
        this.baseDamage = ATTACK;
        this.returnToHand = true;
        ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new GainBlockAction(p, this.block));
        addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL)));
    }

    @Override
    public void upp() {
        ExhaustiveVariable.upgrade(this, UPGRADE_EXHAUSTIVE);
    }

}
