package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.infestpack.OnInfestCard;
import thePackmaster.orbs.WitchesStrike.FullMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HornetWithin extends AbstractPackmasterCard implements OnInfestCard {
    public final static String ID = makeID("HornetWithin");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HornetWithin() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = 3;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
    @Override
    public void onInfest(int infestCounter) {
        addToBot(new MysticFlourishAction(1));
    }
    public void upp() {
        upgradeDamage(2);
        upgradeBlock(2);
    }
    @Override
    public String cardArtCopy() {
        return DeadlyPoison.ID;
    }
}
