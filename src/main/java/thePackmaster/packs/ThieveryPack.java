package thePackmaster.packs;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.thieverypack.*;

import java.util.ArrayList;

public class ThieveryPack extends AbstractCardPack {
	public static final String ID = SpireAnniversary5Mod.makeID("ThieveryPack");
	private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
	public static final String NAME = UI_STRINGS.TEXT[0];
	public static final String DESC = UI_STRINGS.TEXT[1];
	public static final String AUTHOR = UI_STRINGS.TEXT[2];

	public ThieveryPack() {
		super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(2, 2, 4, 3, 3, "Strength"));
	}

	@Override
	public ArrayList<String> getCards() {
		ArrayList<String> cards = new ArrayList<>();
		cards.add(DrainShield.ID);
		cards.add(FairTrade.ID);
		cards.add(Smokescreen.ID);
		cards.add(NullHammer.ID);
		cards.add(Magnet.ID);
		cards.add(StrengthSap.ID);
		cards.add(CunningPoison.ID);
		cards.add(MindControl.ID);
		cards.add(Witchcraft.ID);
		cards.add(ThieveryMastery.ID);
		return cards;
	}
}
