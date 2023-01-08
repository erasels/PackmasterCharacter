package thePackmaster.cards.weaponspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.weaponspack.EquipAction;
import thePackmaster.orbs.weaponspack.CutlassOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RearmingStrike extends AbstractWeaponsPackCard {
    public static final String ID = makeID("RearmingStrike");
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DAMAGE = 3;
    private static final int WEAPON_ATTACK = 4;
    private static final int WEAPON_DURABILITY = 2;

    public RearmingStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);

        this.baseDamage = this.damage = DAMAGE;

        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new EquipAction(new CutlassOrb(WEAPON_ATTACK, WEAPON_DURABILITY, true)));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DAMAGE);
        }
    }

}
