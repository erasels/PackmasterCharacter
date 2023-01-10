package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntingStrike extends AbstractPackmasterCard {
    public final static String ID = makeID("HuntingStrike");

    public static final int DAMAGE = 7;
    public static final int UPG_DAMAGE = 1;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    int originalBaseDamage;
    boolean isDoubled = false;

    public HuntingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        originalBaseDamage = baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void applyPowers(){
        super.applyPowers();
        AbstractMonster hoveredMonster = AbstractDungeon.getCurrRoom().monsters.hoveredMonster;
        if (hoveredMonster != null){
            if ((hoveredMonster.type == AbstractMonster.EnemyType.ELITE || hoveredMonster.type == AbstractMonster.EnemyType.BOSS)){
                if (!isDoubled) {
                    System.out.println(hoveredMonster.name + "/" + hoveredMonster.type);
                    this.baseDamage = baseDamage * 2;
                    if (AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)) {
                        this.baseDamage += AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount;
                    }
                    isDoubled = true;
                    this.rawDescription = DESCRIPTION;
                    initializeDescription();
                    applyPowers();
                }
        }
        else if (isDoubled){
            this.baseDamage = originalBaseDamage;
            this.rawDescription = DESCRIPTION;
            isDoubled = false;
            initializeDescription();
            applyPowers();
            }
        }
        else if (isDoubled){
            this.baseDamage = originalBaseDamage;
            this.rawDescription = DESCRIPTION;
            isDoubled = false;
            initializeDescription();
            applyPowers();
        }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeSecondDamage(UPG_DAMAGE*2);
    }
}