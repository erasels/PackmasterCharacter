package thePackmaster.cards.infestpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import thePackmaster.cardmodifiers.infestpack.InfestModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class ScarabShield extends AbstractInfestCard implements OnInfestCard {
    public final static String ID = makeID("ScarabShield");
    // intellij stuff skill, self, uncommon, , , 15, 5, , 

    public ScarabShield() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 15;
        CardModifierManager.addModifier(this, new InfestModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(1));
    }

    @Override
    public void onInfest(int infestCounter) {
        applyToSelf(new ArtifactPower(AbstractDungeon.player, 1));
    }

    public void upp() {
        upgradeBlock(5);
    }
}