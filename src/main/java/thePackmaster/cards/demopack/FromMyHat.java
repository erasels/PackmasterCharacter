/*
package thePackmaster.cards.demopack;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class FromMyHat extends AbstractPackmasterCard {
    public final static String ID = makeID("FromMyHat");
    // intellij stuff skill, self, common, , , 14, 2, , 

    public FromMyHat() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractCard q = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
        CardModifierManager.addModifier(q, new RetainMod());
        makeInHand(q);
    }

    public void upp() {
        upgradeBlock(4);
    }
}

 */