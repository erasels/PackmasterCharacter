package thePackmaster.powers.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.onGenerateCardMidcombatInterface;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FirewallPower
        extends AbstractPackmasterPower
        implements onGenerateCardMidcombatInterface
{
    public static final String POWER_ID = makeID(FirewallPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FirewallPower(AbstractCreature owner)
    {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, 1);
        this.loadRegion("buffer");
    }

    @Override
    public void updateDescription()
    {
        description = String.format(DESCRIPTIONS[0], amount);
    }

    @Override
    public void onCreateCard(AbstractCard card)
    {
        if (card.type == AbstractCard.CardType.STATUS)
        {
            CardGroup grp = null;
            AbstractPlayer p = AbstractDungeon.player;
            if (p.discardPile.contains(card)) grp = p.discardPile;
            else if (p.drawPile.contains(card)) grp = p.drawPile;
            else if (p.hand.contains(card)) grp = p.hand;

            if (grp != null) addToBot(new ExhaustSpecificCardAction(card, grp));
        }
    }
}
