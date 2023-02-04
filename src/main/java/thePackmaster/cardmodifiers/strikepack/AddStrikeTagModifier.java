package thePackmaster.cardmodifiers.strikepack;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifierWithName;

public class AddStrikeTagModifier extends AbstractCardModifier {

    public AddStrikeTagModifier() {
        super();
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(AbstractCard.CardTags.STRIKE);
        card.applyPowers();
        card.superFlash(Color.CHARTREUSE.cpy());
    }


    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return cardName + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("AddStrikeTagModifier")).TEXT[0];
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new AddStrikeTagModifier();
    }
}