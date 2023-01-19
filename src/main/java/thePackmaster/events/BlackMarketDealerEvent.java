//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package thePackmaster.events;

import BattleTowers.BattleTowers;
import BattleTowers.relics.OttosDeck;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.Shame;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;

import java.util.Iterator;

import static BattleTowers.BattleTowers.makeID;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlackMarketDealerEvent extends AbstractImageEvent {
    public static final String ID = makeID("BlackMarketDealerEvent");
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final EventStrings eventStrings;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
    }

    private int screenNum = 0;
    private boolean pickCard = false;
    private boolean pickCard2 = false;

    public BlackMarketDealerEvent() {
        super(NAME, DESCRIPTIONS[0], SpireAnniversary5Mod.makeImagePath("events/dealer.png"));

        if (getUncommonCards().size() == 0 || getRareCards().size() == 0) {
            this.imageEventText.setDialogOption(OPTIONS[0], true);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[1], new OttosDeck());
        }

        if (AbstractDungeon.player.gold >= 75) {
            this.imageEventText.setDialogOption(OPTIONS[2]);
        } else {
            this.imageEventText.setDialogOption(OPTIONS[3], true);
        }

        this.imageEventText.setDialogOption(OPTIONS[4]);
        //this.imageEventText.setDialogOption(OPTIONS[5]);


    }

    public void update() {
        super.update();
        if ((this.pickCard || this.pickCard2) && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            AbstractCard c = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            CardCrawlGame.sound.play("CARD_EXHAUST");
            AbstractDungeon.topLevelEffects.add(new com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect(

                    c, Settings.WIDTH / 2, Settings.HEIGHT / 2));


            AbstractDungeon.player.masterDeck.removeCard(c);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();

            if (this.pickCard2) {
                this.imageEventText.updateBodyText(DESCRIPTIONS[6]);
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[5]);
                this.screenNum = 1;
                this.pickCard2 = false;
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2, Settings.HEIGHT / 2, new OttosDeck());

            } else {
                AbstractDungeon.gridSelectScreen.open(getRareCards(), 1, OPTIONS[6], false, false, false, true);
                pickCard2 = true;
                this.pickCard = false;

            }

        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch (this.screenNum) {
            case 0:
                switch (buttonPressed) {
                    case 0:
                        this.pickCard = true;
                        this.imageEventText.clearAllDialogs();
                        AbstractDungeon.gridSelectScreen.open(getUncommonCards(), 1, OPTIONS[6], false, false, false, true);

                        return;
                    case 1:
                        this.screenNum = 1;

                        boolean ottoWins = AbstractDungeon.miscRng.randomBoolean(0.5F);
                        if (ottoWins) {
                            AbstractDungeon.player.loseGold(75);
                            this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[2]);
                        } else {
                            AbstractDungeon.player.gainGold(75);
                            AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.RainingGoldEffect(75));
                            CardCrawlGame.sound.play("GOLD_GAIN");
                            this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[3]);
                        }
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        return;
                    case 2:
                        this.screenNum = 1;
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Shame(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
                        AbstractDungeon.player.gainGold(75);
                        AbstractDungeon.effectList.add(new com.megacrit.cardcrawl.vfx.RainingGoldEffect(75));
                        CardCrawlGame.sound.play("GOLD_GAIN");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[1] + DESCRIPTIONS[4]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        return;
                    case 3:
                        this.screenNum = 1;
                        this.imageEventText.updateBodyText(DESCRIPTIONS[5]);
                        this.imageEventText.clearAllDialogs();
                        this.imageEventText.setDialogOption(OPTIONS[5]);
                        return;
                }
            case 1:

                this.openMap();
        }

    }

    public static CardGroup getUncommonCards() {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.rarity == AbstractCard.CardRarity.UNCOMMON) {
                retVal.group.add(c);
            }
        }

        return retVal;
    }

    public static CardGroup getRareCards() {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Iterator var2 = AbstractDungeon.player.masterDeck.group.iterator();

        while (var2.hasNext()) {
            AbstractCard c = (AbstractCard) var2.next();
            if (c.rarity == AbstractCard.CardRarity.RARE) {
                retVal.group.add(c);
            }
        }

        return retVal;
    }
}
