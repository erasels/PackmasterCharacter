package thePackmaster.cards.downfallpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.downfallpack.DeEnergizedPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class HyperBeam extends AbstractDownfallCard {
    public final static String ID = makeID("HyperBeam");


    private static final int DAMAGE = 28;

    public HyperBeam() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ALL_ENEMY);

        baseDamage = DAMAGE;
        this.isMultiDamage = true;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.hb.cX, p.hb.cY, p.flipHorizontal), 0.1F));
        atb(new WaitAction(0.1F));

        allDmg(AbstractGameAction.AttackEffect.NONE);
        atb(new ApplyPowerAction(p, p, new DeEnergizedPower(magicNumber), magicNumber));

    }

    @Override
    public void upp() {
        upgradeMagicNumber(-1);

    }


}
