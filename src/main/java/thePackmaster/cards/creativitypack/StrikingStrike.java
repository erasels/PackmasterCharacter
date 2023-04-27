package thePackmaster.cards.creativitypack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.FlexibleDiscoveryAction;
import thePackmaster.util.creativitypack.JediUtil;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikingStrike extends AbstractCreativityCard {

    public final static String ID = makeID(StrikingStrike.class.getSimpleName());

    public StrikingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void upp() {
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        CardGroup cards = JediUtil.filterCardsForDiscovery(c -> c.hasTag(CardTags.STRIKE) && !c.hasTag(CardTags.HEALING) && c.rarity != CardRarity.SPECIAL && c.rarity != CardRarity.BASIC);
        if (upgraded) {
            cards.group.forEach(AbstractCard::upgrade);
        }
        addToBot(new FlexibleDiscoveryAction(JediUtil.createCardsForDiscovery(cards), selectedCard -> {
            CardModifierManager.addModifier(selectedCard, new ExhaustMod());
            },
                true));
    }
}
