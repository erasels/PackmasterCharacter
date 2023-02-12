package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Strike;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StrikePlus extends AbstractPackmasterCard implements OnObtainCard {
    public final static String ID = makeID("StrikePlus");

    public StrikePlus() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, "basics");
        this.cardsToPreview = new Strike();
        this.baseMagicNumber = this.magicNumber = 5;
        this.exhaust = true;
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Strike(), Settings.HEIGHT / 2, Settings.HEIGHT / 2));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = this.cardsToPreview;
        for(int i = 0; i<this.magicNumber;i++){
            card.upgraded = false;
            card.upgrade();
        }
        card.name = card.originalName + "+" + card.timesUpgraded;
        addToBot(new MakeTempCardInHandAction(card));
    }

    public void upp(){
        upgradeMagicNumber(2);
    }
}
