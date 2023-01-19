package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.hats.HatMenu;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public abstract class AbstractCardPack {
    public String packID;
    public String name;
    public String description;
    public String author;
    public String creditsHeader;
    public String credits;
    public ArrayList<AbstractCard> cards;
    public AbstractCard previewPackCard;
    public boolean hatHidesHair;

    public AbstractCardPack(String id, String name, String description, String author, String credits) {
        this.packID = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.credits = credits;
        this.creditsHeader = CardCrawlGame.languagePack.getUIString(makeID("CreditsRenderStrings")).TEXT[0];
        this.cards = new ArrayList<>();
        hatStrings = CardCrawlGame.languagePack.getUIString(this.packID + "Hat");
        initializePack();
    }

    public AbstractCardPack(String id, String name, String description, String author) {
        this(id, name, description, author, null);
    }

    public abstract ArrayList<String> getCards();

    public void initializePack() {
        for (String s : getCards()) {
            AbstractCard c = CardLibrary.getCard(s);
            if (c == null) {
                System.out.println("CARD FOR PACK NOT FOUND: " + s);
                //And then it'll crash.
            }
            SpireAnniversary5Mod.cardParentMap.put(c.cardID, packID);
            SpireAnniversary5Mod.cardClassParentMap.put(c.getClass(), packID);
            cards.add(c.makeStatEquivalentCopy());
        }
        previewPackCard = makePreviewCard();
    }

    public AbstractCard makePreviewCard() {
        return new CardPackPreview(packID, this);
    }

    public ArrayList<String> getPackPotions() {
        return new ArrayList<>();
    }

    private UIStrings hatStrings;

    public String getHatName() {
        if (hatStrings == null)
            return this.name;
        return hatStrings.TEXT[1];
    }

    public String getHatFlavor() {
        if (hatStrings == null)
            return HatMenu.TEXT[4] + this.name + HatMenu.TEXT[5];
        return hatStrings.TEXT[0];
    }

}
