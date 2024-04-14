package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.showmanpack.NextTrickPower;
import thePackmaster.util.Wiz;

import javax.smartcardio.Card;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForMyNextTrick extends AbstractPackmasterCard {
    public final static String ID = makeID("ForMyNextTrick");
    // intellij stuff skill, self, basic, , ,  5, 3, ,
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    public ForMyNextTrick() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NextTrickPower(p, magicNumber)));
        AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.POWER).makeCopy();
        this.addToBot(new MakeTempCardInHandAction(card));
        addToBot(new TalkAction(true, EXTENDED_DESCRIPTION[0] + card.name + EXTENDED_DESCRIPTION[1], 0.6f, 1.6f));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}