package thePackmaster.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.powers.ConjurePackPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjurePack extends AbstractPackmasterCard {
    public final static String ID = makeID("ConjurePack");

    public ConjurePack() {
        super(ID, 0, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);

        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCardPack> choices = SpireAnniversary5Mod.getRandomPacks(true, magicNumber);

        ArrayList<AbstractCard> packCards = new ArrayList<>();

        for (AbstractCardPack pack:choices) {
            packCards.add(new PackmasterModalChoiceCard(pack.previewPackCard, true, () -> action(pack)));
        }

        addToBot(new EasyModalChoiceAction(packCards, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[0]));
    }

    public void action(AbstractCardPack pack) {
        Wiz.applyToSelf(new ConjurePackPower(Wiz.p(), 5, pack));
    }

    public void upp() {
        isInnate = true;
    }
}