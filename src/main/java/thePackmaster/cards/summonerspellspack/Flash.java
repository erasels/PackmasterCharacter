package thePackmaster.cards.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class Flash extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Flash");
    private static final int COST = 0;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Flash() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        magicNumber = baseMagicNumber = MAGIC;
        exhaust = true;
        cardsToPreview = new Hexflash();
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
        AbstractCard c = new Hexflash();
        c.upgrade();
        cardsToPreview = c;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(p, magicNumber));

        AbstractCard c = new Hexflash();
        if (upgraded)
            c.upgrade();
        this.addToBot(new MakeTempCardInHandAction(c, 1));
    }
}
