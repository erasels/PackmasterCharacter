package thePackmaster.cards.lockonpack.special;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cardmodifiers.lockonpack.GlockOnModifier;
import thePackmaster.cards.lockonpack.AbstractLockonCard;
import thePackmaster.patches.hermitpack.EnumPatch;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DC extends AbstractLockonCard {

    public final static String ID = makeID(DC.class.getSimpleName());

    public DC() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        selfRetain = true;
        exhaust = true;
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Lightning orb = new Lightning();
        addToBot(new ChannelAction(orb));
        if (upgraded) orb.onEndOfTurn();
        orb.onEvoke();
    }
}
