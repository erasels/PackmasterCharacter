package thePackmaster.cards.startuppack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.startuppack.InstinctsPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InstinctiveThinking extends AbstractStartUpCard {
    public final static String ID = makeID("InstinctiveThinking");

    public InstinctiveThinking() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        Wiz.applyToSelf(new InstinctsPower(abstractPlayer, this.magicNumber));
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(3);
    }
}