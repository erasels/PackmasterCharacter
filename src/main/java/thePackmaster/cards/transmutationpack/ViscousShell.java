package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;
import thePackmaster.cardmodifiers.transmutationpack.GainBlockEffect;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class ViscousShell extends AbstractHydrologistCard implements TransmutableCard {
    public final static String ID = makeID("ViscousShell");
    // intellij stuff SKILL, SELF, COMMON, , , 5, 3, , 

    public ViscousShell() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, Subtype.WATER);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, p, block));
        atb(new TransmuteCardAction(this));
    }

    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public ArrayList<AbstractExtraEffectModifier> getMutableAbilities() {
        ArrayList<AbstractExtraEffectModifier> list = new ArrayList<>();
        list.add(new GainBlockEffect(this, true, 1));
        return list;
    }
}