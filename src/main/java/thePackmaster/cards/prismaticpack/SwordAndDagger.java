package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class SwordAndDagger extends AbstractPrismaticCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SwordAndDagger");
    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int SHIVS = 1;
    private static final int UPGRADE_SHIVS = 1;

    public SwordAndDagger() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = SHIVS;
        this.cardsToPreview = new Shiv();
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_SHIVS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToBot(new MakeTempCardInHandAction(new Shiv(), this.magicNumber));

    }
}
