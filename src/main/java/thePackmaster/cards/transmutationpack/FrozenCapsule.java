package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;
import thePackmaster.cardmodifiers.transmutationpack.DamageEffect;
import thePackmaster.cardmodifiers.transmutationpack.DrawCardEffect;
import thePackmaster.cardmodifiers.transmutationpack.GainBlockEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class FrozenCapsule extends AbstractHydrologistCard implements TransmutableCard {
    public final static String ID = makeID("FrozenCapsule");
    // intellij stuff attack, enemy, rare, 8, , , , 1, 1

    public FrozenCapsule() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, Subtype.ICE);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        hydrologistDamage(p, m, damage);
        atb(new DrawCardAction(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public ArrayList<AbstractExtraEffectModifier> getMutableAbilities() {
        ArrayList<AbstractExtraEffectModifier> list = new ArrayList<>();
        list.add(new DamageEffect(this, true, 1));
        list.add(new DrawCardEffect(this, true, 1));
        return list;
    }
}