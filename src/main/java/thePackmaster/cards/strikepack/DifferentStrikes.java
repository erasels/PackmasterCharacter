package thePackmaster.cards.strikepack;

import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.Strike;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DifferentStrikes extends AbstractStrikePackCard {
    public final static String ID = makeID("DifferentStrikes");

    public DifferentStrikes() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

        Wiz.atb(new ExhaustAction(1, false));

        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                AbstractCard strike = Wiz.returnTrulyRandomPrediCardInCombat(card -> card.hasTag(CardTags.STRIKE) && !card.hasTag(CardTags.HEALING) && (card.rarity == CardRarity.COMMON || card.rarity == CardRarity.UNCOMMON || card.rarity == CardRarity.RARE), true);
                if (strike != null) {
                    strike.modifyCostForCombat(-1);
                    if (upgraded) strike.upgrade();
                    if (!strike.exhaust) CardModifierManager.addModifier(strike, new ExhaustMod());
                    addToBot(new MakeTempCardInHandAction(strike));
                } else { //Give a basic Strike if there were no Strikes in the pool somehow
                    AbstractCard dumbStrike = new Strike();
                    dumbStrike.modifyCostForCombat(-1);
                    if (upgraded) dumbStrike.upgrade();
                    CardModifierManager.addModifier(dumbStrike, new ExhaustMod());
                    addToBot(new MakeTempCardInHandAction(dumbStrike));
                }
            }
        });

    }

    public void upp() {
        upgradeDamage(3);
        //Also makes the generated strike upgraded.
    }
}