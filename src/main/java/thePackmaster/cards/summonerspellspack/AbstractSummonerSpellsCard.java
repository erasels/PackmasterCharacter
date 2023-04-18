package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractSummonerSpellsCard extends AbstractPackmasterCard
{
    public AbstractSummonerSpellsCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target, "summonerspells");
    }

    @Override
    public void upp() {
    }

    static public boolean isOverextended() {
        return AbstractDungeon.player.hand.group.size() >= 6;
    }
}
