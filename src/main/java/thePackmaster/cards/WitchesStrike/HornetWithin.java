package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.green.DeadlyPoison;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.infestpack.OnInfestCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HornetWithin extends AbstractWitchStrikeCard implements OnInfestCard {
    public final static String ID = makeID("HornetWithin");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HornetWithin() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 3;
        magicNumber = baseMagicNumber = 2;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }
    @Override
    public void onInfest(int infestCounter) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToTop(new EvokeOrbAction(magicNumber));
                addToBot(new DecreaseMaxOrbAction(1));
                isDone = true;
            }
        });
    }
    public void upp() {
        upgradeMagicNumber(1);
        upgradeBlock(2);
    }
    @Override
    public String cardArtCopy() {
        return DeadlyPoison.ID;
    }
}
