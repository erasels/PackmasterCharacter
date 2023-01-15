package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.utilitypack.ConjurationAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class Conjuration extends AbstractUtilityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Conjuration");
    private static final int COST = 2;

    public Conjuration() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
    }

    @Override
    public void upp() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        this.addToBot(new ConjurationAction(!this.upgraded));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        boolean hasNoCards = AbstractDungeon.player.drawPile.group.isEmpty();
        if (hasNoCards) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            canUse = false;
        }

        return canUse;
    }
}
