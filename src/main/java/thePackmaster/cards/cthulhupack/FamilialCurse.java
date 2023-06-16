package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FamilialCurse extends AbstractCthulhuCard {
    public final static String ID = makeID("FamilialCurse");

    public FamilialCurse() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseBlock = 10;
        cardsToPreview = new Lunacy();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.forAllMonstersLiving((mo) ->
                Wiz.applyToEnemy(mo, new WeakPower(mo, this.magicNumber, false)));

        loseSanity(4);

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}