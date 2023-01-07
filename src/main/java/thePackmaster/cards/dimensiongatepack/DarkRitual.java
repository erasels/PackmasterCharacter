package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DarkRitual extends AbstractDimensionalCard {
    public final static String ID = makeID("DarkRitual");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public DarkRitual() {
        super(ID, 0, CardRarity.RARE, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);

        setFrame("darkritualframe.png");
        baseMagicNumber = magicNumber = 100;
        FleetingField.fleeting.set(this, true);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = this;
        atb(new LoseHPAction(p, p, 10));
        atb(new GainGoldAction(magicNumber));
        if (upgraded) atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                        q.baseMagicNumber -= 50;
                        q.magicNumber = q.baseMagicNumber;
                        AbstractCard tar = StSLib.getMasterDeckEquivalent(q); // Slightly iffy code here, downgrading is weird.
                        int idx = AbstractDungeon.player.masterDeck.group.indexOf(tar);
                        AbstractDungeon.player.masterDeck.group.set(idx, CardLibrary.getCard(cardID));
                        downgrade();
            }
        });
    }

    public void upp() {
        FleetingField.fleeting.set(this, false);
        upgradeMagicNumber(50);
    }
}