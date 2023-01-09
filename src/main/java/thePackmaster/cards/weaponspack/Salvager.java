package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.weaponspack.SalvagerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Salvager extends AbstractWeaponsPackCard {
    public static final String ID = makeID("Salvager");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final int COST = 1;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int ENERGY_TO_GAIN = 1;
    private static final int UPGRADE_PLUS_ENERGY_TO_GAIN = 1;

    public Salvager() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = ENERGY_TO_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SalvagerPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_ENERGY_TO_GAIN);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

}
