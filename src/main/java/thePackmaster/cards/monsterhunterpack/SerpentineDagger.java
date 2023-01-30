package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SerpentineDagger extends AbstractMonsterHunterCard {
    public final static String ID = makeID("SerpentineDagger");
    public final static int DAMAGE = 8;
    public final static int MAGIC = 2;
    public final static int UPG_DAMAGE = 2;

    public SerpentineDagger() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.exhaust = true;
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new DamageAction(m, new DamageInfo(p, baseDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
        addToBot(new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 2, true, false));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}