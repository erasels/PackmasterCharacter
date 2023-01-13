package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.clawpack.ClawStrengthPower;
import thePackmaster.util.Wiz;

public abstract class AbstractClawCard extends AbstractPackmasterCard
{
    public AbstractClawCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/claw/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/claw/" + type.name().toLowerCase() + ".png"
        );
    }

    public AbstractClawCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    public void ClawUp(int value){
        Wiz.applyToSelf(new ClawStrengthPower(AbstractDungeon.player, value));
    }
}
