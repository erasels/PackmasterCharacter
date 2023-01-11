package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.cards.green.Neutralize;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.cards.purple.Eruption;
import com.megacrit.cardcrawl.cards.purple.Vigilance;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.Cardistry;
import thePackmaster.cards.Rummage;
import thePackmaster.cards.Strike;
import thePackmaster.util.Wiz;

import static org.apache.commons.lang3.math.NumberUtils.min;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BackToBasics extends AbstractBasicsCard{
    public final static String ID = makeID("BackToBasics");

    public BackToBasics() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 3;
        cardToPreview.add(new Bash());
        cardToPreview.add(new Strike_Red());
        cardToPreview.add(new Neutralize());
        cardToPreview.add(new Survivor());
        cardToPreview.add(new Zap());
        cardToPreview.add(new Dualcast());
        cardToPreview.add(new Eruption());
        cardToPreview.add(new Vigilance());
        cardToPreview.add(new Rummage());
        cardToPreview.add(new Cardistry());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, Wiz.hand().size(), true, false));
        for(AbstractCard c : cardToPreview)
            addToBot(new MakeTempCardInHandAction(c));
        if(this.costForTurn > 0) {
            addToBot(new GainEnergyAction(min(magicNumber, this.costForTurn)));
        }
    }

    public void upp(){
        upgradeBaseCost(2);
    }
}
