package thePackmaster.powers.anomalypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.powers.AbstractPackmasterPower;
import java.util.Iterator;

import static thePackmaster.util.Wiz.att;

public class ThoughtweavingPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("ThoughtweavingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final Logger logger = LogManager.getLogger("Packmaster");

    public ThoughtweavingPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    public void onUseCard(AbstractCard tmpCard, UseCardAction action) {
        //logger.info("Check 0");
        if (tmpCard instanceof AbstractPackmasterCard) {
            //logger.info("Check 1");
            AbstractPackmasterCard card = (AbstractPackmasterCard) tmpCard;
            if (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL) {
                this.flash();
                //logger.info("Check 2");
                if (AbstractDungeon.player.hasPower("No Draw")) {
                    //logger.info("Check 11");
                    AbstractDungeon.player.getPower("No Draw").flash();
                } else {
                    //logger.info("Check 3");
                    AbstractCard.CardType typeToFind;

                    AbstractCardPack packToAvoid = card.getParent();

                    if (card.type == AbstractCard.CardType.ATTACK) {
                        //logger.info("Look for Skill");
                        typeToFind = AbstractCard.CardType.SKILL;
                    } else {
                        //logger.info("Look for Attack");
                        typeToFind = AbstractCard.CardType.ATTACK;
                    }

                    int counter = 0;
                    CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    Iterator var2 = AbstractDungeon.player.drawPile.group.iterator();

                    AbstractPackmasterCard c;
                    while (var2.hasNext() && counter < amount) {
                        //logger.info("Check 4");
                        AbstractCard check = (AbstractCard)var2.next();
                        if (check instanceof AbstractPackmasterCard) {
                            c = (AbstractPackmasterCard) check;
                            if (c.type == typeToFind && c.getParent() != packToAvoid) {
                                tmp.addToRandomSpot(c);
                                counter++;
                            }
                        }
                    }

                    for (int i = 0; i < counter; ++i) {
                        if (!tmp.isEmpty()) {
                            //logger.info("Check 5");
                            tmp.shuffle();
                            tmpCard = tmp.getBottomCard();
                            tmp.removeCard(card);
                            if (AbstractDungeon.player.hand.size() == 10) {
                                AbstractDungeon.player.createHandIsFullDialog();
                            } else {
                                AbstractDungeon.player.drawPile.group.remove(tmpCard);
                                AbstractDungeon.player.drawPile.addToTop(tmpCard);
                                this.addToBot(new DrawCardAction(1));
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0] +  DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0] + (amount) + DESCRIPTIONS[2];
        }
    }

}