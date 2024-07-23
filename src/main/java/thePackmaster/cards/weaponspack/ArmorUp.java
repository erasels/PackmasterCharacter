package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.weaponspack.ArmorUpPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ArmorUp extends AbstractWeaponsPackCard {
    public static final String ID = makeID("ArmorUp");
    private static final int COST = 1;
    private static final int BLOCK_TO_GAIN = 2;
    private static final int UPGRADE_PLUS_BLOCK_FOR_WEAPON_ATTACK = 1;

    public ArmorUp() {
        super(ID, COST, AbstractCard.CardType.POWER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = BLOCK_TO_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ArmorUpPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_BLOCK_FOR_WEAPON_ATTACK);
        }
    }
}
