package thePackmaster.patches.rippack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.CorruptionPower;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import thePackmaster.actions.rippack.RipCardAction;
import thePackmaster.cardmodifiers.rippack.ArtCardModifier;
import thePackmaster.cardmodifiers.rippack.RippableModifier;
import thePackmaster.cardmodifiers.rippack.TextCardModifier;
import thePackmaster.cards.rippack.ArtAttack;
import thePackmaster.vfx.rippack.ShowCardAndRipEffect;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.cardmodifiers.rippack.RippableModifier.isRippable;
import static thePackmaster.patches.rippack.AllCardsRippablePatches.RipStatus.ART;
import static thePackmaster.util.Wiz.*;

//Houses patches related to the act of ripping cards
//Intial place the shader is applied when rendering art/text halves

public class AllCardsRippablePatches {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("Rip"));
    private static AbstractGameAction action;
    private static AbstractCard card;

    public static ShaderProgram oldShader = null;
    public static ShaderProgram artShader = null;
    public static ShaderProgram textShader = null;

    public static final Texture PERFORATION = ImageMaster.loadImage(modID + "Resources/images/512/rip/perforation.png");
    public static final Texture PERFORATION_SCV = ImageMaster.loadImage(modID + "Resources/images/1024/rip/perforation.png");

    public enum RipStatus {
        WHOLE,
        ART,
        TEXT
    }

    @SpirePatch(clz = AbstractCard.class, method = SpirePatch.CLASS)
    public static class AbstractCardFields {
        public static SpireField<RipStatus> ripStatus = new SpireField(() -> RipStatus.WHOLE);
    }

    @SpirePatch(clz = AbstractCard.class, method = "update")
    public static class HelloThere {

        @SpirePostfixPatch()
        public static void Postfix(AbstractCard __instance) {
            if (action != null && action.isDone) {
                action = null;
            }
            if (AbstractDungeon.player != null) {
                card = __instance;
                clickUpdate();
            }
        }
    }

    public static void clickUpdate() {
        if (!AbstractDungeon.isScreenUp && HitboxRightClick.rightClicked.get(card.hb) && !AbstractDungeon.actionManager.turnHasEnded) {
            onRightClick();
        }
    }

    public static void onRightClick() {
        if(action == null && isRippable(card)) {
            action = new RipCardAction(card);
            att(action);
            att(new WaitAction(0.1f));
            att(new WaitAction(0.1f));
            att(new WaitAction(0.1f));
            att(new WaitAction(0.1f));
            att(new VFXAction(new ShowCardAndRipEffect(card)));
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy")
    public static class CopyRippedStatus {

        @SpirePostfixPatch
        public static AbstractCard Postfix(AbstractCard __result, AbstractCard __instance) {
            AbstractCardFields.ripStatus.set(__result, AbstractCardFields.ripStatus.get(__instance));
            if(!isWholeCard(__result)) {
                CardModifierManager.removeModifiersById(__result, RippableModifier.ID, true);
                __result.exhaust = true;
            }
            if(isArtCard(__result)) {
                CardModifierManager.addModifier(__result, new ArtCardModifier());
            }
            if(isTextCard(__result)) {
                CardModifierManager.addModifier(__result, new TextCardModifier());
            }
            return __result;
        }
    }

    //I don't want to see the quick attack animation when playing Art Halves of Attack cards
    //Skips over other stuff at the start of useCard, picks up at UseCardAction since I do want playing card type side-effects to happen
    //I'm sorry
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class DontDoStuffWhenArtCardUnlessArtAttackWhoopsLol {

        @SpirePrefixPatch()
        public static SpireReturn Prefix(AbstractPlayer __instance, AbstractCard card, AbstractMonster monster, int energyOnUse) {
            if (AllCardsRippablePatches.AbstractCardFields.ripStatus.get(card) == ART && card.cardID != ArtAttack.ID) {
                AbstractDungeon.actionManager.addToBottom(new UseCardAction(card, monster));
                if (!card.dontTriggerOnUseCard) {
                    __instance.hand.triggerOnOtherCardPlayed(card);
                }
                __instance.hand.removeCard(card);
                __instance.cardInUse = card;
                card.target_x = (Settings.WIDTH / 2);
                card.target_y = (Settings.HEIGHT / 2);
                if (card.costForTurn > 0
                        && !card.freeToPlay()
                        && !card.isInAutoplay
                        && (!__instance.hasPower(CorruptionPower.POWER_ID) || card.type != AbstractCard.CardType.SKILL)) {
                    __instance.energy.use(card.costForTurn);
                }
                if (!__instance.hand.canUseAnyCard() && !__instance.endTurnQueued) {
                    AbstractDungeon.overlayMenu.endTurnButton.isGlowing = true;
                }
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }
    public static boolean setShader = false;

    //I only want my patch in renderHelper below to apply on card backgrounds
    @SpirePatch(clz = AbstractCard.class, method = "renderCardBg")
    public static class SetFlagForRenderHelperPatch {

        public static void Prefix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            setShader = true;
        }
    }

    //Set art/text shaders appropriately
    //Base game cards utilize an atlas that needs to be taken into account when determining the cutoff point where the shader should start
    @SpirePatch(clz = AbstractCard.class, method = "renderHelper", paramtypez = {SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class})
    public static class ApplyArtOrTextShaders {
        @SpirePrefixPatch
        public static void Prefix(AbstractCard __instance, SpriteBatch sb, Color color, TextureAtlas.AtlasRegion img, float drawX, float drawY) {
            oldShader = sb.getShader();
            if (isArtCard(__instance) && setShader) {
                //don't mess with the 5/8, it's how I got the cutoff to line up properly between base game/custom cards. I dunno why it's not exactly the same
                float cutoff_y = ((float)img.getRegionY() + ((float)img.getRegionHeight() * 5 / 8)) / img.getTexture().getHeight();
                cutoff_y = isAtlasUsed(img) ? cutoff_y : 0.6f;
                initArtShader();
                sb.setShader(artShader);
                artShader.setUniformf("u_y", cutoff_y);
            }
            if (isTextCard(__instance) && setShader) {
                float cutoff_y = ((float)img.getRegionY() + ((float)img.getRegionHeight() / 2)) / img.getTexture().getHeight();
                cutoff_y = isAtlasUsed(img) ? cutoff_y : 0.5f;
                initTextShader();
                sb.setShader(textShader);
                textShader.setUniformf("u_y", cutoff_y);
            }
        }

        @SpirePostfixPatch
        public static void PostFix(AbstractCard __instance, SpriteBatch sb) {
            if (!isWholeCard(__instance)) {
                setShader = false;
                sb.setShader(oldShader);
            }
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "renderDescription")
    @SpirePatch(clz = AbstractCard.class, method = "renderDescriptionCN")
    public static class RenderPerforationBeforeDescription {
        @SpirePrefixPatch
        public static void Prefix(AbstractCard __instance, SpriteBatch sb) {
            if(isRippable(__instance)) {
                sb.draw(PERFORATION, __instance.current_x - 256.0f, __instance.current_y - 256.0f,
                        256.0f, 256.0f, 512.0f, 512.0f,
                        __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale,
                        __instance.angle, 0, 0, 512, 512, false, false);
                sb.flush();
            }
        }
    }

    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderDescription")
    @SpirePatch(clz = SingleCardViewPopup.class, method = "renderDescriptionCN")
    public static class RenderPerforationBeforeDescriptionSCV {
        @SpirePrefixPatch
        public static void Prefix(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard ___card) {
            if(isRippable(___card)) {
                sb.draw(PERFORATION_SCV, (Settings.WIDTH / 2) - 512.0f, (Settings.HEIGHT / 2) - 512.0f,
                        512.0f, 512.0f, 1024.0f, 1024.0f,
                        Settings.scale,  Settings.scale,
                        ___card.angle, 0, 0, 1024, 1024, false, false);
                sb.flush();
            }
        }
    }

    //if the region is less than the texture, we're using an atlas
    private static boolean isAtlasUsed(TextureAtlas.AtlasRegion img) {
        return img.getRegionHeight() < img.getTexture().getHeight();
    }


    private static void initArtShader() {
        if (artShader == null) {
            try {
                artShader = new ShaderProgram(
                        Gdx.files.internal(makeShaderPath("rippack/artHalf/vertex.vs")),
                        Gdx.files.internal(makeShaderPath("rippack/artHalf/fragment.fs"))
                );
                if (!artShader.isCompiled()) {
                    System.err.println(artShader.getLog());
                }
                if (artShader.getLog().length() > 0) {
                    System.out.println(artShader.getLog());
                }
            } catch (GdxRuntimeException e) {
                System.out.println("ERROR: Failed to init artHalf shader:");
                e.printStackTrace();
            }
        }
    }

    private static void initTextShader() {
        if (textShader == null) {
            try {
                textShader = new ShaderProgram(
                        Gdx.files.internal(makeShaderPath("rippack/textHalf/vertex.vs")),
                        Gdx.files.internal(makeShaderPath("rippack/textHalf/fragment.fs"))
                );
                if (!textShader.isCompiled()) {
                    System.err.println(textShader.getLog());
                }
                if (textShader.getLog().length() > 0) {
                    System.out.println(textShader.getLog());
                }
            } catch (GdxRuntimeException e) {
                System.out.println("ERROR: Failed to init textHalf shader:");
                e.printStackTrace();
            }

        }
    }
}
