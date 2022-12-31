package thePackmaster.cards;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.util.CardArtRoller;

import static thePackmaster.SpireAnniversary5Mod.modID;

@AutoAdd.Ignore
public class PackmasterModalChoiceCard extends CustomCard {

    private Runnable onUseOrChosen;
    private String passedName;
    private String passedDesc;
    private boolean passedusePackFrame;
    private String passedID;

    public PackmasterModalChoiceCard(String ID, String name, String description, boolean usePackFrame, Runnable onUseOrChosen) {
        super(ID, "", AbstractPackmasterCard.getCardTextureString(ID.replace(modID + ":", ""), CardType.SKILL),
                -2, "", CardType.SKILL, ThePackmaster.Enums.PACKMASTER_RAINBOW, CardRarity.SPECIAL, CardTarget.NONE);

        this.name = this.originalName = passedName = name;
        this.rawDescription = passedDesc = description;
        this.passedusePackFrame = usePackFrame;
        this.onUseOrChosen = onUseOrChosen;
        this.passedID = ID;
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
        return new PackmasterModalChoiceCard(passedID, passedName, passedDesc, passedusePackFrame, onUseOrChosen);
    }
}
