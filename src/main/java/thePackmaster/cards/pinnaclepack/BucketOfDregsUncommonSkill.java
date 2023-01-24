package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BucketOfDregsUncommonSkill extends AbstractPinnacleCard {
    public final static String ID = makeID("BucketOfDregsUncommonSkill");
    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = 4;
    private static final int BLOCK = 3;
    private static final int UPGRADE_BLOCK = 2;


    public BucketOfDregsUncommonSkill() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        this.selfRetain = true;
        this.baseBlock = BLOCK;
    }

    public void onRetained() {
        applyPowers();
        blck();
        for (AbstractMonster XD : Wiz.getEnemies()) {
            addToBot(new VFXAction(new SanctityEffect(XD.hb.cX, XD.hb.cY), 0.1F));
            addToBot(new HealAction(XD, Wiz.p(), this.magicNumber));
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_MAGIC);
        upgradeBlock(UPGRADE_BLOCK);
    }

}
