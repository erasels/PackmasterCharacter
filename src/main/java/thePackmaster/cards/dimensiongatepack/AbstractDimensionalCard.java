package thePackmaster.cards.dimensiongatepack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public abstract class AbstractDimensionalCard extends AbstractPackmasterCard {
    public final static String ID = makeID("PackRat");

    public AbstractDimensionalCard(final String cardID, final int cost, final CardRarity rarity, final AbstractCard.CardType type, final AbstractCard.CardTarget target) {
        super(cardID, cost, type, rarity, target);
    }

    public void setFrame(String img){
        this.setBackgroundTexture("anniv5Resources/images/512/dimension/" + img, "anniv5Resources/images/1024/dimension/" + img);
    }

    @Override
    protected Texture getPortraitImage() {
        return null;
    }

    @SpireOverride
    protected void renderPortrait(SpriteBatch sb) {

        }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}