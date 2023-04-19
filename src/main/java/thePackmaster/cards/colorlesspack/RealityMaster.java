package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class RealityMaster extends AbstractColorlessPackCard {
    public final static String ID = makeID("RealityMaster");
    // intellij stuff power, self, rare, , , , , , 

    public RealityMaster() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MasterRealityPower(p));
    }

    public void upp() {
        isInnate = true;
    }
}