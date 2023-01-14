package thePackmaster.cards.thieverypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractThieveryCard extends AbstractPackmasterCard {
	public AbstractThieveryCard(String ID, int cost, CardType type, CardRarity rarity, CardTarget target) {
		super(ID, cost, type, rarity, target);
		setBackgroundTexture(
			"anniv5Resources/images/512/thieverypack/" + type.name().toLowerCase() + ".png",
			"anniv5Resources/images/1024/thieverypack/" + type.name().toLowerCase() + ".png"
		);
	}

	@Override
	public void upp() {
		// Yay
	}
}
