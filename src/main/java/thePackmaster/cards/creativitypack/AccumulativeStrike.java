package thePackmaster.cards.creativitypack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.JediUtil;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AccumulativeStrike extends AbstractPackmasterCard {

    public final static String ID = makeID(AccumulativeStrike.class.getSimpleName());

    public AccumulativeStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 12;
    }

    @Override
    public void upp() {
        uDesc();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

}
