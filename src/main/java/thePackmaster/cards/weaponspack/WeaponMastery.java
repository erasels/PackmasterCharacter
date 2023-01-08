package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.weaponspack.WeaponMasteryPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WeaponMastery extends AbstractWeaponsPackCard {
    public static final String ID = makeID("WeaponMastery");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int CARDS_TO_DRAW = 1;
    private static final int UPGRADE_PLUS_CARDS_TO_DRAW = 1;

    public WeaponMastery() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = CARDS_TO_DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new WeaponMasteryPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_CARDS_TO_DRAW);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

}
