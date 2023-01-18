package thePackmaster.hats.specialhats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public interface SpecialHat {
    void preRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y);
    void postRenderPlayer(SpriteBatch sb, AbstractPlayer p, float x, float y);
}