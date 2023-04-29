package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cull extends AbstractQuantaCard {
    public final static String ID = makeID("Cull");

    private static final int BLOCK = 7;
    private static final int BLOCK_UP = 3;

    public Cull() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new SelfDamageAction(new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        Wiz.atb(new GainBlockAction(p, p, block));
    }

    public void upp() {
        upgradeBlock(BLOCK_UP);
    }
}
