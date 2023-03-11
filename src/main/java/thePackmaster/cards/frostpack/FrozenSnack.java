package thePackmaster.cards.frostpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FrozenSnack extends AbstractFrostCard {
    public final static String ID = makeID("FrozenSnack");

    public FrozenSnack() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new Snack();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new Snack();
        if (upgraded) c.upgrade();
        CardModifierManager.addModifier(c, new FrozenMod());
        Wiz.atb(new MakeTempCardInHandAction(c));

    }

    public void upp() {
        cardsToPreview.upgrade();

    }
}