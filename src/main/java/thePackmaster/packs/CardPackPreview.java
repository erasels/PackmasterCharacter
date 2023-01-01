package thePackmaster.packs;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class CardPackPreview extends AbstractPackPreviewCard {
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public CardPackPreview(String cardID, AbstractCardPack parentPack) {
        super(cardID, parentPack);
        SpireAnniversary5Mod.cardParentMap.put(cardID, parentPack.packID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {

    }
}