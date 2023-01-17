package thePackmaster.cards.energyandechopack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.energyandechopack.EchoMod;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Voices extends AbstractEchoCard {

    public final static String ID = makeID(Voices.class.getSimpleName());

    private static final int COST = 1;

    public Voices(){
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
        CardModifierManager.addModifier(this, new EchoMod());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upMagic(1);
    }
}
