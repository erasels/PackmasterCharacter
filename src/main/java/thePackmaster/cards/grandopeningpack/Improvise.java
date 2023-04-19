package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.PhantasmalPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Improvise extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Improvise");

    public Improvise() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = this.block = 5;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int cardsDiscarded = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.isInnate && c != this) {
                addToBot(new DiscardSpecificCardAction(c));
                cardsDiscarded++;
            }
        }
        addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
        if(cardsDiscarded > 0)
            addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new PhantasmalPower(abstractPlayer, 1), 1));
    }

    @Override
    public void triggerOnGlowCheck() {
        boolean holdingInnate = false;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.isInnate && c != this) {
                holdingInnate = true;
                break;
            }
        }
        if (holdingInnate) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}