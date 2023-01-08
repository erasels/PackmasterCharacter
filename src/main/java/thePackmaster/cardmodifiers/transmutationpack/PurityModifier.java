package thePackmaster.cardmodifiers.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class PurityModifier extends AbstractCardModifier {
    public static final String ID = SpireAnniversary5Mod.makeID("PurityModifier");
    public static final Texture purityIcon = new Texture("anniv5Resources/images/512/hydrologist/purity.png");
    public int amount;

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
        ExtraIcons.icon(purityIcon).offsetX(-2f).text(String.valueOf(amount)).render(card);
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