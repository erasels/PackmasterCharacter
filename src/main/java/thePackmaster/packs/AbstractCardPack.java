package thePackmaster.packs;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.patches.CardParentPackPatch;

import java.util.ArrayList;

public abstract class AbstractCardPack {
    public String packID;
    public String name;
    public String description;
    public ArrayList<AbstractCard> cards;
    public AbstractCard previewPackCard;

    public AbstractCardPack(String id, String name, String description) {
        this.packID = id;
        this.name = name;
        this.description = description;
        this.cards = new ArrayList<>();
        initializePack();
    }

    public abstract ArrayList<String> getCards();

    public void initializePack() {
        for (String s : getCards()) {
            //TODO - Still something wrong here with the global AbstractCard var initialization.
            //TODO - While we're at it, display it on the card text as a subtype above the type.
            AbstractCard c = CardLibrary.getCard(s);
            CardParentPackPatch.parentPack.set(c, this);
            cards.add(c.makeStatEquivalentCopy());

        }
        previewPackCard = new CardPackPreview(packID, this);

    }
}
