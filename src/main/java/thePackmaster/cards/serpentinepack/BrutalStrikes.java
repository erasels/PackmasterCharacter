package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.powers.boardgamepack.DicePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BrutalStrikes extends AbstractSerpentineCard {

    private static final int COST = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    private static final int DAMAGE = 6;
    public final static String ID = makeID("BrutalStrikes");


    public BrutalStrikes() {
        super(ID, COST, AbstractCard.CardType.ATTACK, CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    public void applyPowers() {
        AbstractPower vigor = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
        AbstractPower dice = AbstractDungeon.player.getPower(DicePower.POWER_ID);
        if (vigor != null) {
            vigor.amount *= this.magicNumber;
        }
        if (dice != null) {
            dice.amount *= this.magicNumber;
        }

        super.applyPowers();

        if (vigor != null) {
            vigor.amount /= this.magicNumber;
        }
        if (dice != null) {
            dice.amount /= this.magicNumber;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPower vigor = AbstractDungeon.player.getPower(VigorPower.POWER_ID);
        AbstractPower dice = AbstractDungeon.player.getPower(DicePower.POWER_ID);
        if (vigor != null) {
            vigor.amount *= this.magicNumber;
        }
        if (dice != null) {
            dice.amount *= this.magicNumber;
        }

        super.calculateCardDamage(mo);

        if (vigor != null) {
            vigor.amount /= this.magicNumber;
        }
        if (dice != null) {
            dice.amount /= this.magicNumber;
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
