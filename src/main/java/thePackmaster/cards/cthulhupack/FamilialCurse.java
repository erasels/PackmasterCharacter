package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.PageOfTheDeadPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FamilialCurse extends AbstractCthulhuCard {
    public final static String ID = makeID("FamilialCurse");

    public FamilialCurse() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 15;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster m2: AbstractDungeon.getCurrRoom().monsters.monsters
             ) {
            //TODO - Apply Ruin
            //Wiz.applyToEnemy(m2, new );
        }

        addToBot(new DiscardAction(p, p, 10, true, false));
    }

    public void upp() {
    }
}