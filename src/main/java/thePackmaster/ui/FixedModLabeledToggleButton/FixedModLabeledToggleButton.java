package thePackmaster.ui.FixedModLabeledToggleButton;

import basemod.IUIElement;
import basemod.ModPanel;
import basemod.patches.com.megacrit.cardcrawl.helpers.TipHelper.HeaderlessTip;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

import java.util.function.Consumer;

// This class is a near-copy of the BaseMod class, changed to use Settings.xScale and Settings.yScale
public class FixedModLabeledToggleButton implements IUIElement {
	private static final float TEXT_X_OFFSET = 40.0f;
	private static final float TEXT_Y_OFFSET = 8.0f;
	
	public FixedModToggleButton toggle;
	public FixedModLabel text;
	public String tooltip;
	
	public FixedModLabeledToggleButton(String labelText, float xPos, float yPos,
									   Color color, BitmapFont font, boolean enabled, ModPanel p,
									   Consumer<FixedModLabel> labelUpdate, Consumer<FixedModToggleButton> c) {
		this(labelText, null, xPos, yPos, color, font, enabled, p, labelUpdate, c);
	}

	public FixedModLabeledToggleButton(String labelText, String tooltipText, float xPos, float yPos,
									   Color color, BitmapFont font, boolean enabled, ModPanel p,
									   Consumer<FixedModLabel> labelUpdate, Consumer<FixedModToggleButton> c) {
		toggle = new FixedModToggleButton(xPos, yPos, enabled, false, p, c);
		text = new FixedModLabel(labelText, xPos + TEXT_X_OFFSET, yPos + TEXT_Y_OFFSET,
				color, font, p, labelUpdate);
		tooltip = tooltipText;
		
		// TODO: implement multi-line text
		toggle.wrapHitboxToText(labelText, 1000.0f, 0.0f, font);
	}
	
	
	@Override
	public void render(SpriteBatch sb) {
		toggle.render(sb);
		text.render(sb);

		if (tooltip != null && toggle.hb.hovered) {
			HeaderlessTip.renderHeaderlessTip(
					InputHelper.mX + 60f * Settings.xScale,
					InputHelper.mY - 50f * Settings.yScale,
					tooltip
			);
		}
	}

	@Override
	public void update() {
		toggle.update();
		text.update();
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
		toggle.set(xPos, yPos);
		text.set(xPos + TEXT_X_OFFSET, yPos + TEXT_Y_OFFSET);
	}

	@Override
	public void setX(float xPos) {
		toggle.setX(xPos);
		text.setX(xPos + TEXT_X_OFFSET);
	}

	@Override
	public void setY(float yPos) {
		toggle.setY(yPos);
		text.setY(yPos + TEXT_Y_OFFSET);
	}

	@Override
	public float getX() {
		return toggle.getX();
	}

	@Override
	public float getY() {
		return toggle.getY();
	}
}