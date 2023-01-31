package thePackmaster.cards.scrypluspack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractScryPlusCard extends AbstractPackmasterCard
{
    public AbstractScryPlusCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, (String)null);
    }

    protected void setDmg(int num) {
        baseDamage = num;
    }

    protected void setBlock(int num) {
        baseBlock = num;
    }

    protected void setMN(int num) {
        baseMagicNumber = magicNumber = num;
    }

    protected void setSecondMN(int num) {
        baseSecondMagic = secondMagic = num;
    }

    protected void scryFlash() {
        flash(); //TODO: make it cool looking divinity-style
    }
}
