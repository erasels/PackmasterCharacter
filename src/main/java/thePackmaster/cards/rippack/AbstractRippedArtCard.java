package thePackmaster.cards.rippack;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.*;

public abstract class AbstractRippedArtCard extends AbstractRipCard {

    AbstractRippableCard sourceCard;
    public static ShaderProgram shader = null;
    private static ArrayList<TooltipInfo> consumableTooltip;

    public AbstractRippedArtCard(String cardID, AbstractRippableCard sourceCard, CardColor color) {
        super(cardID, sourceCard.cost, sourceCard.type, CardRarity.SPECIAL, CardTarget.NONE, color, getCardTextureString(sourceCard.cardID.replace(modID + ":", ""), sourceCard.type));
        this.sourceCard = sourceCard;
        setDisplayRarity(sourceCard.rarity);
        exhaust = true;
    }

    public AbstractRippedArtCard(String cardID, AbstractRippableCard sourceCard) {
        this(cardID, sourceCard, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        if(sourceCard != null) {
            sourceCard.upgradeJustSource();
        }
    }

    @Override
    public void upp() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (consumableTooltip == null)
        {
            consumableTooltip = new ArrayList<>();
            consumableTooltip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("art_card")), BaseMod.getKeywordDescription(makeID("art_card"))));
        }
        List<TooltipInfo> compoundList = new ArrayList<>(consumableTooltip);
        if (super.getCustomTooltipsTop() != null) compoundList.addAll(super.getCustomTooltipsTop());
        return compoundList;
    }

    @Override
    public void render(SpriteBatch sb) {
        initShader();
        sb.setShader(shader);
        super.render(sb);
        sb.setShader(null);
    }

    @Override
    public void renderHoverShadow(SpriteBatch sb) {

    }

    private static void initShader() {
        if (shader == null) {
            try {
                shader = new ShaderProgram(
                        Gdx.files.internal(makeShaderPath("rippack/artHalf/vertex.vs")),
                        Gdx.files.internal(makeShaderPath("rippack/artHalf/fragment.fs"))
                );
                if (!shader.isCompiled()) {
                    System.err.println(shader.getLog());
                }
                if (shader.getLog().length() > 0) {
                    System.out.println(shader.getLog());
                }
            } catch (GdxRuntimeException e) {
                System.out.println("ERROR: Failed to init artHalf shader:");
                e.printStackTrace();
            }
        }
    }
}
