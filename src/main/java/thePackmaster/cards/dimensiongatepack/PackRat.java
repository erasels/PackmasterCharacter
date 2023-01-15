package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
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

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PackRat extends AbstractDimensionalCard {
    public final static String ID = makeID("PackRat");

    public PackRat() {
        super(ID, 2, CardRarity.UNCOMMON, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);
        baseBlock = 14;
        setFrame("packratframe.png");
        FleetingField.fleeting.set(this, true);
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
                    if (upgraded) {
                        baseBlock = 14;
                        upgradedBlock = false;
                        // Purge this from masterdeck if unupgraded, downgrade it from masterdeck otherwise. -> Fleeting + specialty code.
                        AbstractCard tar = StSLib.getMasterDeckEquivalent(q); // Slightly iffy code here, downgrading is weird.
                        int idx = AbstractDungeon.player.masterDeck.group.indexOf(tar);
                        AbstractDungeon.player.masterDeck.group.set(idx, CardLibrary.getCard(cardID));
                        downgrade();

                    }
                }
            }
        });
    }

    public void upp() {
        FleetingField.fleeting.set(this, false);
        upgradeBlock(3);
    }
}