package thePackmaster.cards.womaninbluepack;

import basemod.AutoAdd;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.PackmasterModalChoiceCard;

@AutoAdd.Ignore
public class AbstractWomanInBlueModalChoiceCard extends PackmasterModalChoiceCard {
    public AbstractWomanInBlueModalChoiceCard(String ID, String name, String description, boolean usePackFrame, Runnable onUseOrChosen) {
        super(ID, name, description, usePackFrame, onUseOrChosen);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture(
                "anniv5Resources/images/512/womaninblue/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/womaninblue/" + type.name().toLowerCase() + ".png"
        );
    }

}
