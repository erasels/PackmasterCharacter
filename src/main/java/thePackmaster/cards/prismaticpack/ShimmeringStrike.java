package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public class ShimmeringStrike extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("ShimmeringStrike");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DAMAGE = 6;
    private static final int EXTRA_DAMAGE = 1;
    private static final int UPGRADE_EXTRA_DAMAGE = 1;

    public ShimmeringStrike() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
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
        this.baseDamage += this.magicNumber * this.countDifferentColorCardsPlayedThisCombat();
        super.calculateCardDamage(m);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * this.countDifferentColorCardsPlayedThisCombat();
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    public int countDifferentColorCardsPlayedThisCombat() {
        return (int)AbstractDungeon.actionManager.cardsPlayedThisCombat.stream().filter(c -> c.color != AbstractDungeon.player.getCardColor()).count();
    }
}
