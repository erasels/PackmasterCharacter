package thePackmaster.cards.rippack;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.helpers.TooltipInfo;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx;
import com.megacrit.cardcrawl.vfx.cardManip.CardGlowBorder;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.*;

public abstract class AbstractRippedTextCard extends AbstractRipCard {

    AbstractRippableCard sourceCard;
    public static ShaderProgram shader = null;
    private static ArrayList<TooltipInfo> consumableTooltip;

    public AbstractRippedTextCard(String cardID, AbstractRippableCard sourceCard, CardColor color) {
        super(cardID, 0, sourceCard.type, CardRarity.SPECIAL, sourceCard.target, color);
        this.sourceCard = sourceCard;
        baseMagicNumber = magicNumber = sourceCard.baseMagicNumber;
        baseDamage = damage = sourceCard.baseDamage;
        baseBlock = block = sourceCard.baseBlock;
        setDisplayRarity(sourceCard.rarity);
        exhaust = true;
    }


    public AbstractRippedTextCard(String cardID, AbstractRippableCard sourceCard) {
        this(cardID, sourceCard, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        sourceCard.damage = damage; //I can't get these values to update properly on upgrade
        sourceCard.block = block;
        sourceCard.magicNumber = magicNumber;
        sourceCard.secondMagic = secondMagic;
        sourceCard.use(p, m);
    }

    @Override
    public void upgrade() {
        String prevName = name;
        if(sourceCard != null && !sourceCard.upgraded) {
            sourceCard.upgradeJustSource();
        }
        super.upgrade();
        name = prevName;
        initializeTitle();
    }

    @Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (consumableTooltip == null)
        {
            consumableTooltip = new ArrayList<>();
            consumableTooltip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("text_card")), BaseMod.getKeywordDescription(makeID("text_card"))));
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
                        Gdx.files.internal(makeShaderPath("rippack/textHalf/vertex.vs")),
                        Gdx.files.internal(makeShaderPath("rippack/textHalf/fragment.fs"))
                );
                if (!shader.isCompiled()) {
                    System.err.println(shader.getLog());
                }
                if (shader.getLog().length() > 0) {
                    System.out.println(shader.getLog());
                }
            } catch (GdxRuntimeException e) {
                System.out.println("ERROR: Failed to init textHalf shader:");
                e.printStackTrace();
            }
        }
    }
}
