package thePackmaster.cards.basicspack;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.CardArtRoller;

import java.util.ArrayList;

public abstract class AbstractMultipleCardPreviewCard extends AbstractPackmasterCard {
    private float rotationTimer = 0;
    private int previewIndex;
    protected ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    private boolean needsArtRefresh = false;

    public AbstractMultipleCardPreviewCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, String frameID)
    {
        super(cardID, cost, type, rarity, target, frameID);
    }

    public AbstractMultipleCardPreviewCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
    }

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
        if (!cardToPreview.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    cardsToPreview = cardToPreview.get(previewIndex);
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    protected float getRotationTimeNeeded() {
        return 1f;
    }
}

