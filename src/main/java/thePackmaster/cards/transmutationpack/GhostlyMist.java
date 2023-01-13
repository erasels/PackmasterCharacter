package thePackmaster.cards.transmutationpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.cardmodifiers.transmutationpack.*;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class GhostlyMist extends AbstractHydrologistCard implements TransmutableCard {
    public final static String ID = makeID("GhostlyMist");
    // intellij stuff skill, none, rare, , , , , 5, 

    public GhostlyMist() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.STEAM);
        shuffleBackIntoDrawPile = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new TransmuteCardAction(this));
    }

    public void upp() {
        isInnate = true;
    }

    @Override
    public boolean freeToPlay() {
        return true;
    }

    @Override
    public ArrayList<AbstractExtraEffectModifier> getMutableAbilities() {
        ArrayList<AbstractExtraEffectModifier> list = new ArrayList<>();
        list.add(new FreeToPlayEffect(true));
        list.add(new TransmuteSelfEffect(this, true));
        list.add(new ReboundSelfEffect(true));
        return list;
    }
}