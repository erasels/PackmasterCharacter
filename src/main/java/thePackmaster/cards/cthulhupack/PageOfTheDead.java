package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.curses.Necronomicurse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.NamelessMistPower;
import thePackmaster.powers.cthulhupack.PageOfTheDeadPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PageOfTheDead extends AbstractCthulhuCard {
    public final static String ID = makeID("PageOfTheDead");

    public PageOfTheDead() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        cardsToPreview = new Necronomicurse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded) addToBot(new MakeTempCardInHandAction(new Necronomicurse()));
        Wiz.applyToSelf(new PageOfTheDeadPower(p, 1));

    }

    public void upp() {
    }
}