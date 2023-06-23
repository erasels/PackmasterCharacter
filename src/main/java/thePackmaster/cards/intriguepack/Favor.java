package thePackmaster.cards.intriguepack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.energyandechopack.EchoedEtherealMod;
import thePackmaster.cardmodifiers.energyandechopack.GlowEchoMod;
import thePackmaster.util.Wiz;

import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Favor extends AbstractIntrigueCard {
    public final static String ID = makeID("Favor");

    public Favor() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // List of rares.
        List<AbstractCard> eligibleCards = Wiz.getCardsMatchingPredicate(c -> c.rarity == CardRarity.RARE && !c.hasTag(AbstractCard.CardTags.HEALING));

        // Add one of them to hand.
        if (eligibleCards.size() > 0) {
            AbstractCard cardy = eligibleCards.get(AbstractDungeon.cardRandomRng.random(0, eligibleCards.size() - 1)).makeCopy();
            cardy.setCostForTurn(0);
            CardModifierManager.addModifier(cardy, new ExhaustMod());
            CardModifierManager.addModifier(cardy, new EchoedEtherealMod());
            CardModifierManager.addModifier(cardy, new GlowEchoMod());
            Wiz.atb(new MakeTempCardInHandAction(cardy, true));
        }
    }

    public void upp() {
        this.exhaust = false;
    }
}
