package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.sneckopack.RandomizeCostAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TailFlail extends AbstractSneckoCard {


    public final static String ID = makeID("TailFlail");

    public TailFlail() {
        super(ID, 2, AbstractCard.CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        baseSecondDamage = 16;
        baseMagicNumber = magicNumber = 2;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if(secondDamage > damage)
            damage = AbstractDungeon.cardRandomRng.random(damage, secondDamage);
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new RandomizeCostAction(magicNumber));
    }

    public void upp() {
        upgradeDamage(6);
        upgradeSecondDamage(2);
    }
}
