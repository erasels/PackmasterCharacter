package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;

public abstract class AbstractCardPack {
    public String packID;
    public String name;
    public String description;
    public String author;
    public ArrayList<AbstractCard> cards;
    public AbstractCard previewPackCard;

    public AbstractCardPack(String id, String name, String description, String author) {
        this.packID = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.cards = new ArrayList<>();
        initializePack();
    }

    public abstract ArrayList<String> getCards();

    public void initializePack() {
        for (String s : getCards()) {
            AbstractCard c = CardLibrary.getCard(s);
            SpireAnniversary5Mod.cardParentMap.put(c.cardID, packID);
            SpireAnniversary5Mod.cardClassParentMap.put(c.getClass(), packID);
            cards.add(c.makeStatEquivalentCopy());
        }
        previewPackCard = new CardPackPreview(packID, this);
    }

}
