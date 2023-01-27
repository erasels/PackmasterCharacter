package thePackmaster.cards.dimensiongateabstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public abstract class AbstractDimensionalCard extends AbstractPackmasterCard {
    public final static String ID = "";

    public AbstractDimensionalCard(final String cardID, final int cost, final CardRarity rarity, final AbstractCard.CardType type, final AbstractCard.CardTarget target, final String frameID) {
        super(cardID, cost, type, rarity, target, frameID);
    }

    public AbstractDimensionalCard(final String cardID, final int cost, final CardRarity rarity, final AbstractCard.CardType type, final AbstractCard.CardTarget target, final String frameID, final CardColor color) {
        super(cardID, cost, type, rarity, target, color, frameID);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}