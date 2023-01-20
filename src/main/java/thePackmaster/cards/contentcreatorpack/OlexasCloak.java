package thePackmaster.cards.contentcreatorpack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.HoardPayoffAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OlexasCloak extends AbstractContentCard {
    public final static String ID = makeID("OlexasCloak");

    public OlexasCloak() {
        super(ID, -1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 18;
        PersistFields.setBaseValue(this, 2);
        HoardField.setBaseValue(this, 3);
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HoardPayoffAction(this, () -> blck()));
    }

    public void upp() {
        upgradeBlock(6);
    }
}