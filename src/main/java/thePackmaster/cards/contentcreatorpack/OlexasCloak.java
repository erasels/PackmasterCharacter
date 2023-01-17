package thePackmaster.cards.contentcreatorpack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.HoardPayoffAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.cardvars.HoardField;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OlexasCloak extends AbstractPackmasterCard {
    public final static String ID = makeID("OlexasCloak");
    // intellij stuff skil, self, common, , , 18, 6, , 

    public OlexasCloak() {
        super(ID, -1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF);
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