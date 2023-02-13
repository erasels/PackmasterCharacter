package thePackmaster.cards.basicspack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.basics.ConjureCardsAction;
import thePackmaster.cardmodifiers.basicspack.DuplicateModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjureDefends extends AbstractPackmasterCard {
    public final static String ID = makeID("ConjureDefends");

    public ConjureDefends() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.exhaust = true;
        this.cardsToPreview = new Defend();
        this.cardsToPreview.upgrade();
        this.cardsToPreview.rawDescription += CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[1] + "X" + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[2];
        this.cardsToPreview.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = new Defend();
        if(this.upgraded){
            addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse+1, card));
        } else addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse, card));
        if(this.energyOnUse>0 && !this.freeToPlayOnce) {
            addToBot(new GainEnergyAction(1));
        }
    }

    public void upp(){
    }
}
