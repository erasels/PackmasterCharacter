package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.weaponspack.EquipAction;
import thePackmaster.orbs.weaponspack.ForgingHammerOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForgingHammer extends AbstractWeaponsPackCard {

    public static final String ID = makeID("ForgingHammer");
    private static final int COST = 1;
    private static final int WEAPON_ATTACK = 3;
    private static final int WEAPON_DURABILITY = 4;
    private static final int UPGRADE_PLUS_WEAPON_DURABILITY = 1;

    public ForgingHammer() {
        super(ID, COST,  CardType.SKILL,CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = WEAPON_DURABILITY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new EquipAction(new ForgingHammerOrb(WEAPON_ATTACK, this.magicNumber, false)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAPON_DURABILITY);
        }
    }

}
