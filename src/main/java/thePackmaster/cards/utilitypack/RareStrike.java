package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;
import java.util.List;

public class RareStrike extends AbstractUtilityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("RareStrike");
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int EXTRA_DAMAGE = 2;
    private static final int UPGRADE_EXTRA_DAMAGE = 2;

    public RareStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = EXTRA_DAMAGE;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_EXTRA_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * this.countRares();
        super.calculateCardDamage(m);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * this.countRares();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public int countRares() {
        List<AbstractCard> cards = new ArrayList<>();
        cards.addAll(AbstractDungeon.player.hand.group);
        cards.addAll(AbstractDungeon.player.drawPile.group);
        cards.addAll(AbstractDungeon.player.discardPile.group);
        return (int)cards.stream().filter(c -> c.rarity == CardRarity.RARE).count();
    }
}
