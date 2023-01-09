package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.actions.basicspack.KitSelectAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public abstract class AbstractKitCard extends AbstractBasicsCard {

    public AbstractKitCard(String ID, AbstractCard c1, AbstractCard c2) {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.cardToPreview.add(c1);
        this.cardToPreview.add(c2);
        this.cardToPreview.get(0).upgrade();
        this.cardToPreview.get(1).upgrade();
        this.exhaust = true;
    }

    public AbstractKitCard(String ID, AbstractCard c1, AbstractCard c2, CardRarity rarity) {
        super(ID, 0, CardType.SKILL, rarity, CardTarget.SELF);
        this.cardToPreview.add(c1);
        this.cardToPreview.add(c2);
        this.cardToPreview.get(0).upgrade();
        this.cardToPreview.get(1).upgrade();
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new KitSelectAction(this.upgraded, cardToPreview.get(0), cardToPreview.get(1)));
    }

    public void upp(){

    }
}