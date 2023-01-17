package thePackmaster.cards.energyandechopack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.energyandechopack.EchoMod;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ShadowStrike extends AbstractEchoCard {

    public final static String ID = makeID(ShadowStrike.class.getSimpleName());

    private static final int COST = 1;

    public ShadowStrike(){
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        tags.add(CardTags.STRIKE);
        baseDamage = 8;
        CardModifierManager.addModifier(this, new EchoMod());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}
