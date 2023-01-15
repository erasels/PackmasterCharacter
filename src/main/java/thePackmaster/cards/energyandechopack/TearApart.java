package thePackmaster.cards.energyandechopack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.SunderAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.cardmodifiers.energyandechopack.EchoMod;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TearApart extends AbstractPackmasterCard {

    public final static String ID = makeID(TearApart.class.getSimpleName());

    private static final int COST = 2;

    public TearApart(){
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 17;
        CardModifierManager.addModifier(this, new EchoMod());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            this.addToBot(new WaitAction(0.8F));
        }

        this.addToBot(new SunderAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 1));    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }
}
