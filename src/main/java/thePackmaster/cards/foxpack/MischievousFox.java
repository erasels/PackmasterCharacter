package thePackmaster.cards.foxpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MischievousFox extends AbstractFoxCard {
    public final static String ID = makeID("MischievousFox");

    public static final int DAMAGE = 10;
    public static final int MAGIC = 4;
    public static final int UPG_DAMAGE = 2;

    public MischievousFox() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        int rand = AbstractDungeon.cardRng.random(0, 1);
        if (rand == 0){
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }

}
