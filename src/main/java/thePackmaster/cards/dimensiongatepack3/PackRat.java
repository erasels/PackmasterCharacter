package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardInscryp;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PackRat extends AbstractDimensionalCardInscryp {
    public final static String ID = makeID("PackRat");

    public PackRat() {
        super(ID, 2, CardRarity.UNCOMMON, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);
        baseBlock = 14;
        tags.add(CardTags.HEALING);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = this;
        blck();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                boolean foundPot = false;
                isDone = true;
                for (AbstractPotion q : p.potions) {
                    if (!(q instanceof PotionSlot)) {
                        foundPot = true;
                    }
                }
                if (!foundPot){
                    att(new ObtainPotionAction(PotionHelper.getRandomPotion()));
                    // Purge this from masterdeck if unupgraded, downgrade it from masterdeck otherwise.
                    AbstractCard c = StSLib.getMasterDeckEquivalent(q);
                    if (upgraded) {
                        baseBlock = 14;
                        upgradedBlock = false;
                        // Slightly iffy code here, downgrading is weird.
                        if (c != null) {
                            int idx = AbstractDungeon.player.masterDeck.group.indexOf(c);
                            AbstractDungeon.player.masterDeck.group.set(idx, CardLibrary.getCard(cardID));
                        }
                        downgrade();
                    }
                    else {
                        // We can't use Fleeting directly because that gets locked in when a card is played
                        // Instead, we just do the same things Fleeting does ourselves
                        purgeOnUse = true;
                        if (c != null) {
                            AbstractDungeon.player.masterDeck.removeCard(c);
                        }
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}