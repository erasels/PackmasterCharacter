package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.legacypack.MassProductionAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MassProduction extends AbstractPackmasterCard {
    public final static String ID = makeID("MassProduction");

    private static final int UPGRADE_COST = 2;
    public MassProduction() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new MassProductionAction(cardStrings.EXTENDED_DESCRIPTION[0]));
    }

    public void upp() {
        this.upgradeBaseCost(UPGRADE_COST);
    }
}