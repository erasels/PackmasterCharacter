package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.util.TriConsumer;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Prophecy extends AbstractPackmasterCard {

    public static final String ID = SpireAnniversary5Mod.makeID("Prophecy");


    public Prophecy() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    public void setType(CardType type) {

    }

    public void setMultiDamage() {
        isMultiDamage = true;
    }
}
