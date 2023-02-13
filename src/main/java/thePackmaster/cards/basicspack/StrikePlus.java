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

public class StrikePlus extends AbstractPackmasterCard {
    public final static String ID = makeID("StrikePlus");

    public StrikePlus() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.cardsToPreview = new Strike();
        for(int i = 0; i<5;i++){
            this.cardsToPreview.upgraded = false;
            this.cardsToPreview.upgrade();
        }
        this.cardsToPreview.name = this.cardsToPreview.originalName + "+" + this.cardsToPreview.timesUpgraded;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview));
    }

    public void upp(){
        for(int i = 0; i<2;i++){
            this.cardsToPreview.upgraded = false;
            this.cardsToPreview.upgrade();
        }
        this.cardsToPreview.name = this.cardsToPreview.originalName + "+" + this.cardsToPreview.timesUpgraded;
    }
}
