package thePackmaster.ui.FixedModLabeledToggleButton;

import basemod.IUIElement;
import basemod.ModPanel;
import basemod.helpers.UIElementModificationHelper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.function.Consumer;

// This class is a near-copy of the BaseMod class, changed to use Settings.xScale and Settings.yScale
public class FixedModToggleButton implements IUIElement {
	private static final float TOGGLE_Y_DELTA = 0f;
	private static final float TOGGLE_X_EXTEND = 12.0f;
	private static final float HB_WIDTH_EXTENDED = 200.0f;
	
	private Consumer<FixedModToggleButton> toggle;
	Hitbox hb;
	private float x;
	private float y;
	private float w;
	private float h;
	private boolean extendedHitbox;
	
	public boolean enabled;
	public ModPanel parent;

	public FixedModToggleButton(float xPos, float yPos, ModPanel p, Consumer<FixedModToggleButton> c) {
		this(xPos, yPos, false, true, p, c);
	}
	
	public FixedModToggleButton(float xPos, float yPos, boolean enabled, boolean extendedHitbox, ModPanel p, Consumer<FixedModToggleButton> c) {
		x = xPos * Settings.xScale;
		y = yPos * Settings.yScale;
		w = ImageMaster.OPTION_TOGGLE.getWidth();
		h = ImageMaster.OPTION_TOGGLE.getHeight();
		this.extendedHitbox = extendedHitbox;
		if (extendedHitbox) {
			hb = new Hitbox(x - TOGGLE_X_EXTEND * Settings.xScale,
					y - TOGGLE_Y_DELTA * Settings.yScale,
					HB_WIDTH_EXTENDED * Settings.xScale, h * Settings.yScale);
		} else {
			hb = new Hitbox(x, y - TOGGLE_Y_DELTA * Settings.yScale,
					w * Settings.xScale, h * Settings.yScale);
		}

		this.enabled = enabled;
		parent = p;
		toggle = c;
	}
	
	public void wrapHitboxToText(String text, float lineWidth, float lineSpacing, BitmapFont font) {
		float tWidth = FontHelper.getSmartWidth(font, text, lineWidth, lineSpacing);
		hb.width = tWidth + h * Settings.xScale + TOGGLE_X_EXTEND;
	}
	
	public void render(SpriteBatch sb) {
        if (this.hb.hovered) {
            sb.setColor(Color.CYAN);
        } else if (this.enabled) {
            sb.setColor(Color.LIGHT_GRAY);
        } else {
            sb.setColor(Color.WHITE);
        }
        sb.draw(ImageMaster.OPTION_TOGGLE, x, y, w*Settings.xScale, h*Settings.yScale);
        if (this.enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.OPTION_TOGGLE_ON, x, y, w*Settings.xScale, h*Settings.yScale);
        }
        this.hb.render(sb);
	}
	
	public void update() {
		hb.update();
		
		if (hb.justHovered) {
			CardCrawlGame.sound.playV("UI_HOVER", 0.75f);
		}
		
		if (hb.hovered) {
            if (InputHelper.justClickedLeft) {
                CardCrawlGame.sound.playA("UI_CLICK_1", -0.1f);
                hb.clickStarted = true;
            }
		}
		
        if (hb.clicked) {
            hb.clicked = false;
            onToggle();
        }
	}
	
	private void onToggle() {
		this.enabled = !enabled;
		toggle.accept(this);
	}

	public void toggle() {
		onToggle();
	}
	
	@Override
	public int renderLayer() {
		return ModPanel.MIDDLE_LAYER;
	}

	@Override
	public int updateOrder() {
		return ModPanel.DEFAULT_UPDATE;
	}

	@Override
	public void set(float xPos, float yPos) {
		x = xPos*Settings.xScale;
		y = yPos*Settings.yScale;

		if (extendedHitbox) {
			UIElementModificationHelper.moveHitboxByOriginalParameters(hb, x - TOGGLE_X_EXTEND * Settings.xScale,  y - TOGGLE_Y_DELTA * Settings.yScale);
		} else {
			UIElementModificationHelper.moveHitboxByOriginalParameters(hb, x, y - TOGGLE_Y_DELTA * Settings.yScale);
		}
	}

	@Override
	public void setX(float xPos) {
		set(xPos, y/Settings.yScale);
	}

	@Override
	public void setY(float yPos) {
		set(x/Settings.xScale, yPos);
	}

	@Override
	public float getX() {
		return x/Settings.yScale;
	}

	@Override
	public float getY() {
		return y/Settings.xScale;
	}
}