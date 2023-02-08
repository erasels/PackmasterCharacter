package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.ConstructPower;
import thePackmaster.powers.entropypack.RuinPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Waning extends AbstractEntropyCard {
    public final static String ID = makeID("Waning");

    public Waning() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 22;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new RuinPower(m, this.magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractPlayer p = AbstractDungeon.player;

        forAllMonstersLiving(
                (m)->att(new ApplyPowerAction(m, p, new RuinPower(m, this.magicNumber)))
        );
        att(new ApplyPowerAction(p, p, new RuinPower(p, this.magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(6);
    }
}