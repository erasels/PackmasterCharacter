package thePackmaster.cardmodifiers.pixiepack;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.rippack.ArtCardModifier;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PaintcanModifier  extends AbstractCardModifier {
    private int color;

    public static String ID = makeID("PaintcanModifier");

    public PaintcanModifier(int color)
    {
        this.color = color;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if(color == 0) color = AbstractDungeon.cardRandomRng.random(1,4);
        switch (color)
        {
            case 1:
                card.color= AbstractCard.CardColor.RED;
                card.superFlash(Color.RED);
                break;
            case 2:
                card.color= AbstractCard.CardColor.GREEN;
                card.superFlash(Color.GREEN);
                break;
            case 3:
                card.color= AbstractCard.CardColor.BLUE;
                card.superFlash(Color.BLUE);
                break;
            case 4:
                card.color= AbstractCard.CardColor.PURPLE;
                card.superFlash(Color.PURPLE);
                break;
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PaintcanModifier(color);
    }

}
