package thePackmaster.powers.metapack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DiehardPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DiehardPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private static int diehardIDOffset;

    public DiehardPower(AbstractCreature owner, int amount) {
        super(POWER_ID+diehardIDOffset, NAME, PowerType.BUFF, true, owner, amount);

        // Prevent offset from screwing up the icons.
        Texture normalTexture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/DiehardPower32.png");
        Texture hiDefImage = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/DiehardPower84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        ++diehardIDOffset;
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
        if (this.amount == 1) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new WeakPower(owner, 1, false), 1));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new FrailPower(owner, 1, false), 1));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
