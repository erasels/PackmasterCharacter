package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.util.TriConsumer;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Prophecy extends AbstractOracleCard {

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
        this.type = type;

    }

    public void addTarget(CardTarget t) {
        boolean mustTarget = false;
        boolean targetsSelf = false;
        boolean targetsAll = false;
        //sorry about this, it's not very readable, I didn't know how else to do it
        if ((t == AbstractCard.CardTarget.ENEMY || t == AbstractCard.CardTarget.SELF_AND_ENEMY) ||
                (target == AbstractCard.CardTarget.ENEMY || target == AbstractCard.CardTarget.SELF_AND_ENEMY)) {
            mustTarget = true;
        }
        if ((t == AbstractCard.CardTarget.SELF || t == AbstractCard.CardTarget.SELF_AND_ENEMY || t == AbstractCard.CardTarget.ALL) ||
                (target == AbstractCard.CardTarget.SELF || target == AbstractCard.CardTarget.SELF_AND_ENEMY || target == AbstractCard.CardTarget.ALL)) {
            targetsSelf = true;
        }
        if ((t == AbstractCard.CardTarget.ALL || t == AbstractCard.CardTarget.ALL_ENEMY) ||
                (target == AbstractCard.CardTarget.ALL || target == AbstractCard.CardTarget.ALL_ENEMY)) {
            targetsAll = true;
        }

        if (mustTarget && targetsSelf) target = CardTarget.SELF_AND_ENEMY;
        else if (mustTarget && !targetsSelf) target = CardTarget.ENEMY;
        else if (!mustTarget && targetsAll && targetsSelf) target = CardTarget.ALL;
        else if (!mustTarget && targetsAll && !targetsSelf) target = CardTarget.ALL_ENEMY;
        else if (!mustTarget && !targetsAll && targetsSelf) target = CardTarget.SELF;
        else target = CardTarget.NONE;

    }

    public void setMultiDamage() {
        isMultiDamage = true;
    }
}
