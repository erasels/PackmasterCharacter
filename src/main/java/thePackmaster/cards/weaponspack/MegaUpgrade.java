package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.weaponspack.UpgradeWeaponsAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MegaUpgrade extends AbstractWeaponsPackCard {

    public static final String ID = makeID("MegaUpgrade");
    private static final int COST = 1;
    private static final int UPGRADE_VALUE = 3;
    private static final int UPGRADE_PLUS_UPGRADE_VALUE = 2;

    public MegaUpgrade() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = UPGRADE_VALUE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new UpgradeWeaponsAction(this.magicNumber, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_UPGRADE_VALUE);
        }
    }

}
