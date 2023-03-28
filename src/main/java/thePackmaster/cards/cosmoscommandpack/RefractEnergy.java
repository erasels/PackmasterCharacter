package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.cards.marisapack.AmplifyCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.vfx;

public class RefractEnergy extends AbstractCosmosCard implements AmplifyCard {
    public final static String ID = makeID("RefractEnergy");

    public RefractEnergy() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = 14;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal));
        allDmg(AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReduceCostAction(this));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }

    public void upp() {
        upgradeDamage(4);
    }
}