package thePackmaster.cards.foxpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ErraticFox extends AbstractFoxCard {
    public final static String ID = makeID("ErraticFox");

    public static final int DAMAGE = 8;
    public static final int BLOCK = 8;
    public static final int UPG = 2;

    public ErraticFox() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int rand = AbstractDungeon.cardRng.random(0, 1);
        if (rand == 0){
            addToBot(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
        else {
            addToBot(new GainBlockAction(p, block));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPG);
        upgradeBlock(UPG);
    }

}
