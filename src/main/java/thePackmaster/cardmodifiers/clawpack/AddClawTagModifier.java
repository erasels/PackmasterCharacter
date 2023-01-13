package thePackmaster.cardmodifiers.clawpack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cards.clawpack.ClawForOne;

public class AddClawTagModifier extends AbstractMadScienceModifier {

    public AddClawTagModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        if (!card.hasTag(SpireAnniversary5Mod.CLAW)){
            card.tags.add(SpireAnniversary5Mod.CLAW);
            card.applyPowers();
        }
    }

    @Override
    public void alterName(AbstractCard card) {
        modifyName(card.name + " Claw", card);
        super.alterName(card);
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new AddClawTagModifier(value);
    }
}