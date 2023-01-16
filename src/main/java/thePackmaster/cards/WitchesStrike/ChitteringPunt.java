package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Backflip;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.actions.witchesstrikepack.MoonlightBarrageAction;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChitteringPunt extends AbstractPackmasterCard {
    public final static String ID = makeID("ChitteringPunt");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public ChitteringPunt() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        CardModifierManager.addModifier(this, new InfestModifier());
        CardModifierManager.addModifier(this,new InscribedMod(true,false));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (int i = 0; i < InfestModifier.getInfestCount(this); i++){
            addToBot(new ManifestAction(new CrescentMoon()));
        }
    }

    public void upp() {
        upgradeDamage(2);
    }
    @Override
    public String cardArtCopy() {
        return Backflip.ID;
    }
}