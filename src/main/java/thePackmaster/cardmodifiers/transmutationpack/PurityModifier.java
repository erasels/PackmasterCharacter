package thePackmaster.cardmodifiers.transmutationpack;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class PurityModifier extends AbstractCardModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("PurityModifier");
    public static final TextureRegion purityIcon = new TextureAtlas.AtlasRegion(new Texture("anniv5Resources/images/512/hydrologist/purity.png"), 0, 0, 512, 512);
    public int amount;
    private static final float PURITY_TEXT_OFFSET_X = -132.0f;
    private static final float PURITY_TEXT_OFFSET_Y = 125.0f;

    public PurityModifier(int amount) {
        this.amount = amount;
    }

    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster monster) {
        return damage + amount;
    }

    public float modifyBlock(float block, AbstractCard card) {
        return block + amount;
    }

    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        ReflectionHacks.privateMethod(AbstractCard.class, "renderHelper", SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class)
                .invoke(card, sb, Color.WHITE.cpy(), purityIcon, card.current_x, card.current_y);
        BitmapFont font = ReflectionHacks.privateMethod(AbstractCard.class, "getEnergyFont").invoke(card);
        FontHelper.renderRotatedText(sb, font, String.valueOf(amount), card.current_x, card.current_y, PURITY_TEXT_OFFSET_X * card.drawScale * Settings.scale, PURITY_TEXT_OFFSET_Y * card.drawScale * Settings.scale, card.angle, false, Color.WHITE.cpy());
    }

    public boolean shouldApply(AbstractCard card) {
        if (CardModifierManager.hasModifier(card, ID)) {
            PurityModifier mod = (PurityModifier)CardModifierManager.getModifiers(card, ID).get(0);
            mod.amount += amount;
            card.initializeDescription();
            return false;
        }
        return true;
    }

    public AbstractCardModifier makeCopy() {
        return new PurityModifier(amount);
    }
}