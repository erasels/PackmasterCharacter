package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.ConstructPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Construct extends AbstractEntropyCard {
    public final static String ID = makeID("Construct");
    // intellij stuff skill, self, uncommon, , , , , 4, 2

    public Construct() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ConstructPower(p, this.magicNumber));
        applyToSelf(new WeakPower(p, 2, false));
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractPlayer p = AbstractDungeon.player;

        forAllMonstersLiving(
                (m)->att(new ApplyPowerAction(m, p, new WeakPower(m, 2, false)))
        );
        att(new ApplyPowerAction(p, p, new WeakPower(p, 2, false)));

        forAllMonstersLiving(
                (m)->att(new ApplyPowerAction(m, p, new ConstructPower(m, this.magicNumber)))
        );
        att(new ApplyPowerAction(p, p, new ConstructPower(p, this.magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}