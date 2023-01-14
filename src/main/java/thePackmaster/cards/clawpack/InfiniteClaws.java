package thePackmaster.cards.clawpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.clawpack.InfiniteClawsPower;
import thePackmaster.util.Wiz;

import java.util.HashSet;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class InfiniteClaws extends AbstractClawCard {
    public final static String ID = makeID("InfiniteClaws");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public InfiniteClaws() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new GhostClaw(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelfTop(new InfiniteClawsPower(p, 1));
    }

    public void upp() {
        this.isInnate = true;
    }

}