package thePackmaster.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.PackPreviewCard;
import thePackmaster.packs.AlignmentPack;
import thePackmaster.packs.ArcanaPack;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;
import thePackmaster.util.CardArtRoller;

import java.util.HashMap;
import java.util.Map;

import static thePackmaster.SpireAnniversary5Mod.modID;

@AutoAdd.Ignore
public class PackmasterModalChoiceCard extends CustomCard {
    private final CustomCard originalPreview;

    private Runnable onUseOrChosen;
    private boolean passedusePackFrame;

    public PackmasterModalChoiceCard(CustomCard previewCard, boolean usePackFrame, Runnable onUseOrChosen) {
        super(previewCard.cardID, "", previewCard.textureImg,
                -2, "", CardType.SKILL, ThePackmaster.Enums.PACKMASTER_RAINBOW, CardRarity.SPECIAL, CardTarget.NONE);

        this.name = this.originalName = previewCard.name;
        this.rawDescription = previewCard.rawDescription;
        this.passedusePackFrame = usePackFrame;
        this.onUseOrChosen = onUseOrChosen;
        this.originalPreview = previewCard;

        initializeTitle();
        initializeDescription();

        this.portrait = previewCard.portrait;
        this.jokePortrait = previewCard.jokePortrait;
        if (this.passedusePackFrame){
            this.setBackgroundTexture("anniv5Resources/images/512/boosterpackframe.png", "anniv5Resources/images/1024/boosterpackframe.png");
        }

        if (AnimatedCardsPatch.AnimationInfo.isAnimated.get(previewCard)) {
            if (AnimatedCardsPatch.AnimationInfo.cardFrames.containsKey(previewCard.cardID)) {
                AnimatedCardsPatch.loadFrames(this,
                        AnimatedCardsPatch.AnimationInfo.cardFrames.get(previewCard.cardID).length,
                        AnimatedCardsPatch.AnimationInfo.frameRate.get(previewCard.cardID));
            }
        }
    }

    public PackmasterModalChoiceCard(String ID, String name, String description, boolean usePackFrame, Runnable onUseOrChosen) {
        super(ID, "", AbstractPackmasterCard.getCardTextureString(ID.replace(modID + ":", ""), CardType.SKILL),
                -2, "", CardType.SKILL, ThePackmaster.Enums.PACKMASTER_RAINBOW, CardRarity.SPECIAL, CardTarget.NONE);

        this.name = this.originalName = name;
        this.rawDescription = description;
        this.passedusePackFrame = usePackFrame;
        this.onUseOrChosen = onUseOrChosen;
        this.originalPreview = null;

        initializeTitle();
        initializeDescription();

        if (this.passedusePackFrame){
            this.setBackgroundTexture("anniv5Resources/images/512/boosterpackframe.png", "anniv5Resources/images/1024/boosterpackframe.png");
        }
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        } else {
            return super.getPortraitImage();
        }
    }

    @Override
    public void onChoseThisOption() {
        onUseOrChosen.run();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUseOrChosen.run();
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new PackmasterModalChoiceCard(originalPreview == null ? this : originalPreview, passedusePackFrame, onUseOrChosen);
    }
}
