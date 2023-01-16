package thePackmaster.cards.farmerpack;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Iterator;

public abstract class AbstractFarmerCard extends AbstractPackmasterCard {
    public AbstractFarmerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/farmer/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/farmer/" + type.name().toLowerCase() + ".png"
        );
    }
    private boolean attack = false;
    private boolean skill = false;
    private boolean status = false;
    private boolean power = false;
    private boolean curse = false;
    private int count = 0;
    public int checkTypes(){
        Iterator<AbstractCard> typeDetect = AbstractDungeon.actionManager.cardsPlayedThisTurn.iterator();
        while (typeDetect.hasNext()) {
            AbstractCard i = typeDetect.next();
            if (i.type == CardType.ATTACK && !attack) {
                count += 1;
                attack = true;
            } else if (i.type == CardType.SKILL && !skill) {
                count += 1;
                skill = true;
            } else if (i.type == CardType.POWER && !power) {
                count += 1;
                power = true;
            } else if (i.type == CardType.STATUS && !status) {
                count += 1;
                status = true;
            } else if (i.type == CardType.CURSE && !curse) {
                count += 1;
                curse = true;
            }
        }
        return count;
    }

    public AbstractFarmerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);

    }
}
