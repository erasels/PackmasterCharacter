package thePackmaster.cards.summonerspellspack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class UnleashedSmite extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("UnleashedSmite");
    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int DAMAGE = 0;
    private static final int PERSIST = 1;
    private static final int UPG_PERSIST = 1;

    public UnleashedSmite() {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        PersistFields.setBaseValue(this, PERSIST);
    }

    @Override
    public void upp() {
        PersistFields.upgrade(this, UPG_PERSIST);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = AbstractDungeon.player.hand.size() * MAGIC;
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        this.initializeDescription();
    }

    public void applyPowers() {
        int tmp = baseDamage;
        this.baseDamage = AbstractDungeon.player.hand.size() * MAGIC;
        super.applyPowers();
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;

        this.rawDescription = this.upgraded ?
                cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] :
                cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];

        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = baseDamage;
        this.baseDamage = AbstractDungeon.player.hand.size() * MAGIC;
        super.calculateCardDamage(mo);
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }
}
