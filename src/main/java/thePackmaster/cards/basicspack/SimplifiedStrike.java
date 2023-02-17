package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SimplifiedStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("SimplifiedStrike");

    public SimplifiedStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, "basics");
        this.tags.add(CardTags.STRIKE);
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseDamage = this.damage = 0;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int basicCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.rarity == CardRarity.BASIC)
                basicCount++;
        }
        this.baseDamage = this.magicNumber * basicCount;
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void applyPowers() {
        int basicCount = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.rarity == CardRarity.BASIC)
                basicCount++;
        }
        if (basicCount > 0) {
            this.baseDamage = this.magicNumber * basicCount;
            super.applyPowers();
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }
        super.applyPowers();
        initializeDescription();
    }

    public void upp(){
        this.isUnnate = true;
    }
}
