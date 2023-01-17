package thePackmaster.cards.thieverypack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractThieveryCard extends AbstractPackmasterCard {
	public AbstractThieveryCard(String ID, int cost, CardType type, CardRarity rarity, CardTarget target) {
		super(ID, cost, type, rarity, target,"thieverypack");

	}

	@Override
	public void upp() {
		// Yay
	}
}
