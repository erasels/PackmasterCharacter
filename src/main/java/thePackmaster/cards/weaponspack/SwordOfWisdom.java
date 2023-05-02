package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.weaponspack.EquipAction;
import thePackmaster.orbs.weaponspack.SwordOfWisdomOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class SwordOfWisdom extends AbstractWeaponsPackCard {

    public static final String ID = makeID("SwordOfWisdom");
    private static final int COST = -2;
    private static final int WEAPON_ATTACK = 3;
    private static final int WEAPON_DURABILITY = 3;
    private static final int UPGRADE_PLUS_WEAPON_DURABILITY = 1;

    public SwordOfWisdom() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS, getCardTextureString(Sword.ID.replace(modID + ":", ""), CardType.SKILL));

        this.baseMagicNumber = this.magicNumber = WEAPON_DURABILITY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption(){
        AbstractDungeon.actionManager.addToBottom(new EquipAction(new SwordOfWisdomOrb(WEAPON_ATTACK, this.magicNumber, false)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS_WEAPON_DURABILITY);
        }
    }

}
