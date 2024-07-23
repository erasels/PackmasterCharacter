package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;
import thePackmaster.cardmodifiers.transmutationpack.DamageEffect;
import thePackmaster.cardmodifiers.transmutationpack.GainBlockEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Drench extends AbstractHydrologistCard implements TransmutableCard {
    public final static String ID = makeID("Drench");
    // intellij stuff ATTACK, ENEMY, UNCOMMON, 6, 2, 5, 2, , 

    public Drench() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, Subtype.WATER);
        baseDamage = 6;
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        hydrologistDamage(p, m, damage);
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }

    @Override
    public ArrayList<AbstractExtraEffectModifier> getMutableAbilities() {
        ArrayList<AbstractExtraEffectModifier> list = new ArrayList<>();
        list.add(new GainBlockEffect(this, true, 1));
        list.add(new DamageEffect(this, true, 1));
        return list;
    }
}