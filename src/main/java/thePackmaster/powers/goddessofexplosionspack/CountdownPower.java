package thePackmaster.powers.goddessofexplosionspack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.goddessofexplosionspack.AtomicPiledriver;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class CountdownPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("CountdownPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private static int idOffset;
    private final boolean upgraded;

    public CountdownPower(AbstractCreature owner, int amount, boolean upgrade) {
        super(POWER_ID+idOffset, NAME, PowerType.BUFF, false, owner, amount);

        // Prevent offset from screwing up the icons.
        Texture normalTexture = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/CountdownPower32.png");
        Texture hiDefImage = TexLoader.getTexture(SpireAnniversary5Mod.modID + "Resources/images/powers/CountdownPower84.png");
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        ++idOffset;
        upgraded = upgrade;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (amount > 1)
            reducePower(1);
        else {
            AbstractCard ap = new AtomicPiledriver().makeCopy();
            if (upgraded)
                ap.upgrade();

            Wiz.att(new GainEnergyAction(2));
            Wiz.att(new MakeTempCardInHandAction(ap, 1, false));
            Wiz.att(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void updateDescription() {
        if (upgraded)
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}