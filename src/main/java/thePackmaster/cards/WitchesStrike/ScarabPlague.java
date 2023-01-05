package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.infestpack.OnInfestCard;
import thePackmaster.orbs.WitchesStrike.FullMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScarabPlague extends AbstractPackmasterCard implements OnInfestCard {
    public final static String ID = makeID("ScarabPlague");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ScarabPlague() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        baseBlock = 7;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        blck();
    }
    @Override
    public void onInfest(int infestCounter) {
        addToBot(new ManifestAction(new FullMoon()));
    }
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
}