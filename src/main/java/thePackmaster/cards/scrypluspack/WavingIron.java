package thePackmaster.cards.scrypluspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.scrypluspack.ScryCallbackAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WavingIron extends AbstractScryPlusCard{

    public final static String ID = makeID(WavingIron.class.getSimpleName());
    public WavingIron() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        setDmg(7);
        setBlock(7);
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
        addToBot(new ScryCallbackAction(magicNumber, list ->
        {
            if (!list.isEmpty()) {
                Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
            }
        }));
    }
}
