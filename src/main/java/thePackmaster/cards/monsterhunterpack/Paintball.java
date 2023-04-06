package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlickCoinEffect;
import thePackmaster.actions.monsterhunterpack.MarkEnemyAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.HuntersMark;
import thePackmaster.powers.monsterhunterpack.TempRetainCardsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Paintball extends AbstractMonsterHunterCard {
    public final static String ID = makeID("Paintball");

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;
    private static final int MAGIC2 = 1;

    public Paintball() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new FlickCoinEffect(p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY), 0.2F));
        addToBot(new WaitAction(0.2f));
        addToBot(new MarkEnemyAction(p, m, magicNumber));
        addToBot(new ApplyPowerAction(m, p, new HuntersMark(m, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(p, p, new TempRetainCardsPower(p, secondMagic)));
    }

    public void upp() {
        upgradeSecondMagic(UPG_MAGIC);
        upgradeMagicNumber(UPG_MAGIC);
    }
}