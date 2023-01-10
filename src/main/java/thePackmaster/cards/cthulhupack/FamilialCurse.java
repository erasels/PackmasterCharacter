package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.entropypack.RuinPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FamilialCurse extends AbstractCthulhuCard {
    public final static String ID = makeID("FamilialCurse");

    public FamilialCurse() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 15;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.forAllMonstersLiving((mo)->
                Wiz.applyToEnemy(mo, new RuinPower(mo, this.magicNumber)));
        addToBot(new DiscardAction(p, p, 999, true, false));
    }

    public void upp() {
    }
}