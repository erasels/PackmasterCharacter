package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class StrikeThroughFate extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("StrikeThroughFate");
    private static final int ATTACK_DMG = 7;
    private static final int COST = 0;


    public StrikeThroughFate() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.damage = this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                new DamageInfo(p, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 7) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY));
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_HEAVY));

        }

    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractPackmasterCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 6)  {
            this.glowColor = AbstractPackmasterCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
