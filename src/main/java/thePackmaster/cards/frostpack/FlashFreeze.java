package thePackmaster.cards.frostpack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class FlashFreeze extends AbstractFrostCard {
    public final static String ID = makeID("FlashFreeze");

    public FlashFreeze() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[0], card -> ((!CardModifierManager.hasModifier(card, FrozenMod.ID))), (cards) -> {
            att(new SimpleAddModifierAction(new FrozenMod(), cards.get(0), false));

        }));
    }

    public void upp() {
        upgradeDamage(3);
    }
}