package thePackmaster.cards.warriorpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.applyToSelf;

public class WarSong extends AbstractWarriorCard {

    public final static String ID = makeID(WarSong.class.getSimpleName());

    private static final int COST = 1;

    public WarSong(){
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        secondMagic = baseSecondMagic = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("INTIMIDATE"));
        this.addToBot(new VFXAction(p, new IntimidateEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 1.0F));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, this.secondMagic), this.secondMagic));
    }

    @Override
    public void upp() {
        upMagic(1);
        upSecondMagic(1);
    }
}
