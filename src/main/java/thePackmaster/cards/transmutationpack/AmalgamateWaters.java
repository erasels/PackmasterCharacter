package thePackmaster.cards.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;
import thePackmaster.actions.transmutationpack.DrawFilteredCardsAction;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class AmalgamateWaters extends AbstractHydrologistCard {
    public final static String ID = makeID("AmalgamateWaters");
    // intellij stuff , none, rare, , , , , , 

    public AmalgamateWaters() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.WATER);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawFilteredCardsAction(magicNumber, card -> {
            if (card instanceof TransmutableCard) {
                TransmutableCard trans = (TransmutableCard) card;
                if (trans.getMutableAbilities().size() > 0) {
                    return true;
                }
            }
            for (AbstractCardModifier mod : CardModifierManager.modifiers(card)) {
                if (mod instanceof AbstractExtraEffectModifier) {
                    AbstractExtraEffectModifier effect = (AbstractExtraEffectModifier) mod;
                    if (effect.isMutable) {
                        return true;
                    }
                }
            }
            return false;
        }));
        AbstractCard amalgam = this;
        atb(new TransmuteCardAction(true, (oldCard, newCard) -> {
            for (AbstractCardModifier mod : CardModifierManager.modifiers(oldCard)) {
                if (mod instanceof AbstractExtraEffectModifier) {
                    AbstractExtraEffectModifier effect = (AbstractExtraEffectModifier)mod;
                    if (effect.isMutable) {
                        CardModifierManager.addModifier(amalgam, effect.makeCopy());
                    }
                }
            }
            if (oldCard instanceof TransmutableCard) {
                ((TransmutableCard)oldCard).getMutableAbilities().forEach(mod -> {
                    CardModifierManager.addModifier(amalgam, mod);
                });
            }
        }, card -> true));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}