package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.weaponspack.EquipAction;
import thePackmaster.orbs.weaponspack.PowderKegOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PowderKeg extends AbstractWeaponsPackCard {

    public static final String ID = makeID("PowderKeg");
    private static final int COST = 1;
    private static final int WEAPON_ATTACK = 0;
    private static final int WEAPON_DURABILITY = 1;
    private static final int DAMAGE_ON_DESTROY = 15;
    private static final int UPGRADE_PLUS_DAMAGE_ON_DESTROY = 5;

    public PowderKeg() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = DAMAGE_ON_DESTROY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new EquipAction(new PowderKegOrb(WEAPON_ATTACK, WEAPON_DURABILITY, this.magicNumber, false)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_DAMAGE_ON_DESTROY);
        }
    }

}
