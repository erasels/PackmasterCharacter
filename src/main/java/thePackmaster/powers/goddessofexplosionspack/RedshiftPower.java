package thePackmaster.powers.goddessofexplosionspack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class RedshiftPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("RedshiftPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private static int idOffset;

    public RedshiftPower(AbstractCreature owner, int amount) {
        super(POWER_ID+idOffset, NAME, PowerType.BUFF, true, owner, amount);

        // Prevent offset from screwing up the icons.
        Texture normalTexture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/RedshiftPower32.png");
        Texture hiDefImage = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/RedshiftPower84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        ++idOffset;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        Wiz.atb(new DrawCardAction(1));
        Wiz.atb(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}