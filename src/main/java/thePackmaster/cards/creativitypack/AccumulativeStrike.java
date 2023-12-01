package thePackmaster.cards.creativitypack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.creativitypack.AccumulativeDamageModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AccumulativeStrike extends AbstractCreativityCard {

    public final static String ID = makeID(AccumulativeStrike.class.getSimpleName());

    public AccumulativeStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        CardModifierManager.addModifier(this, new AccumulativeDamageModifier(magicNumber));
        baseDamage = damage = 8;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        CardModifierManager.getModifiers(this, AccumulativeDamageModifier.ID).forEach(m -> ((AccumulativeDamageModifier)m).setDamageRamp((this.magicNumber)));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

}
