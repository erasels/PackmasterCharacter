package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.SuckerPunch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.infestpack.OnInfestCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChitteringPunt extends AbstractWitchStrikeCard implements OnInfestCard {
    public final static String ID = makeID("ChitteringPunt");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ChitteringPunt() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return SuckerPunch.ID;
    }

    @Override
    public void onInfest(int infestCounter) {
        addToBot(new ManifestAction(new CrescentMoon()));
    }
}