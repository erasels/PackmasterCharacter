package thePackmaster.powers.showmanpack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

public class HallOfMirrorsPower extends TwoAmountPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("HallOfMirrorsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HallOfMirrorsPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.isTurnBased = false;

        this.name = NAME;

        this.owner = owner;
        this.amount = amount;
        this.amount2 = 2;
        this.type = PowerType.BUFF;

        Texture normalTexture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/" + ID.replaceAll(SpireAnniversary5Mod.modID + ":", "") + "32.png");
        Texture hiDefImage = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/" + ID.replaceAll(SpireAnniversary5Mod.modID + ":", "") + "84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        updateDescription();
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1){
            this.description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1];
        }
        else {
            this.description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (this.amount2 <= 1){
            addToBot(new MakeTempCardInHandAction(new AfterImage(), 1));
            this.amount2 = 2;
        }
        else {
            this.amount2--;
            this.flashWithoutSound();
        }
    }
}
