package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntingStrike extends AbstractMonsterHunterCard {
    public final static String ID = makeID("HuntingStrike");

    public static final int DAMAGE = 8;
    public static final int UPG_DAMAGE = 3;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public HuntingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        this.tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (mo.type == AbstractMonster.EnemyType.BOSS || mo.type == AbstractMonster.EnemyType.ELITE) {
            isDamageModified = true;
            damage *= 2;
        }
    }


    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}