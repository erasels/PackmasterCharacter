package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.clawpack.InfiniteClawsPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InfiniteClaws extends AbstractClawCard {
    public final static String ID = makeID("InfiniteClaws");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public InfiniteClaws() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new GhostClaw();
        tags.add(CLAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelfTop(new InfiniteClawsPower(p, 1));
    }

    public void upp() {
        this.isInnate = true;
    }

}