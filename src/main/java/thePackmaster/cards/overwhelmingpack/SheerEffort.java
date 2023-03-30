package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SheerEffort extends AbstractOverwhelmingCard {
    public final static String ID = makeID("SheerEffort");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HandpickUI"));

    public SheerEffort() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);

        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HandSelectAction(2, card -> true,
                list -> {
                    for (AbstractCard c : list) {
                        Wiz.att(new NewQueueCardAction(c, true, false, true));
                    }
                }, null, uiStrings.TEXT[0], false, false, false)
        );
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}