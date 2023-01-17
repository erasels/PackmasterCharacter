package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SwiftPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.SpikeTrapPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class EatWithoutTable extends AbstractPackmasterCard {
    public final static String ID = makeID(EatWithoutTable.class.getSimpleName());

    public EatWithoutTable() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        exhaust = true;
        cardsToPreview = new Despair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //Drink a random potion
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractPotion potion = null;
                int tries = 0;
                while(tries < 100 && potion == null){
                     AbstractPotion newTry = PotionHelper.getRandomPotion();
                     if(newTry.rarity == AbstractPotion.PotionRarity.COMMON && newTry.canUse() && !newTry.isThrown)
                         potion = newTry;
                     else
                         tries++;
                }
                if(potion == null)
                    potion = new SwiftPotion();

                potion.flash();
                potion.use(Wiz.adp());
                for (AbstractRelic relic: Wiz.adp().relics)
                    relic.onUsePotion();
                UIStrings potionStrings = CardCrawlGame.languagePack.getUIString(modID + ":Potions");
                addToBot(new TalkAction(true, potionStrings.TEXT[0] + potion.name + potionStrings.TEXT[1], 2.5f, 2.5f));
                isDone = true;
            }
        });
        addToBot(new MakeTempCardInHandAction(new Despair()));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}