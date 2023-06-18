package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.watcher.OmniscienceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SheerEffort extends AbstractOverwhelmingCard {
    public final static String ID = makeID("SheerEffort");

    private static final String[] ACTION_TEXT = CardCrawlGame.languagePack.getUIString(makeID("ActuallyFinalFormAction")).TEXT;

    public SheerEffort() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HandSelectAction(2147483632, card -> true,
                list -> {
                    for (AbstractCard c : list) {
                        p.hand.moveToExhaustPile(c);
                    }
                },
                list -> {
                    int max = list.size();
                    addToTop(new AbstractGameAction() {
                        boolean firstRun = true;
                        @Override
                        public void update() {
                            if (firstRun) {
                                firstRun = false;
                                CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                                    if (Wiz.getLogicalCardCost(c) < max) {
                                        temp.addToTop(c);
                                    }
                                }

                                if (temp.isEmpty()) {
                                    this.isDone = true;
                                    return;
                                }

                                temp.sortAlphabetically(true);
                                temp.sortByRarityPlusStatusCardType(false);
                                AbstractDungeon.gridSelectScreen.open(temp, 1, ACTION_TEXT[0] + max + ACTION_TEXT[1], false);
                            }
                            else {
                                if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                                    for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                                        AbstractDungeon.player.drawPile.group.remove(c);
                                        AbstractDungeon.getCurrRoom().souls.remove(c);
                                        this.addToBot(new NewQueueCardAction(c, true, false, true));
                                    }

                                    AbstractDungeon.gridSelectScreen.selectedCards.clear();
                                    AbstractDungeon.player.hand.refreshHandLayout();
                                }
                                this.isDone = true;
                            }
                        }
                    });
                }, ExhaustAction.TEXT[0], false, true, true)
        );
    }

    public void upp() {
        this.upgradeBaseCost(0);
    }
}