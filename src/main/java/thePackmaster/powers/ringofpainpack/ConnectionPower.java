package thePackmaster.powers.ringofpainpack;


import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ConnectionPower extends AbstractPackmasterPower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID(ConnectionPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ConnectionPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 0);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                card.upgrade();
                AbstractPlayer p = AbstractDungeon.player;
                upgradeAllSameCardsInGroup(p.hand, card);
                upgradeAllSameCardsInGroup(p.drawPile, card);
                upgradeAllSameCardsInGroup(p.discardPile, card);
                upgradeAllSameCardsInGroup(p.exhaustPile, card);
                this.isDone = true;
            }
        });
    }

    private void upgradeAllSameCardsInGroup(CardGroup cardGroup, AbstractCard card) {
        for (AbstractCard c : cardGroup.group) {
            if (c.cardID.equals(card.cardID) && c.canUpgrade()) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
                c.upgrade();
                c.applyPowers();
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ConnectionPower(owner);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

}




