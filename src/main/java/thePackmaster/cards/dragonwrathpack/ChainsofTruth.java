package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.actions.dragonwrathpack.SmiteAction;
import thePackmaster.orbs.dragonwrathpack.LightOrb;
import thePackmaster.powers.dragonwrathpack.PenancePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChainsofTruth extends AbstractDragonwrathCard {

    public static final String ID = makeID(ChainsofTruth.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATIO

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public ChainsofTruth(){
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage =DAMAGE;
        this.magicNumber = baseMagicNumber = 3;
        isMultiDamage = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p,baseDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters){
            Wiz.applyToEnemy(monster,new PenancePower(monster,p,magicNumber));
        }
    }


    // Upgraded stats.
    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
