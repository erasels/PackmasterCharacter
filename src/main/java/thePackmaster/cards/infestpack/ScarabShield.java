package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ScarabShield extends AbstractInfestCard {
    public final static String ID = makeID("ScarabShield");
    // intellij stuff skill, self, uncommon, , , 15, 5, , 

    public ScarabShield() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 15;
        baseMagicNumber = magicNumber = 1;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int infestCount = InfestModifier.getInfestCount(this);
        blck();
        if (infestCount >= 2) {
            applyToSelf(new ArtifactPower(p, magicNumber));
        }
    }

    public void upp() {
        upgradeBlock(5);
        upgradeMagicNumber(1);
    }
}