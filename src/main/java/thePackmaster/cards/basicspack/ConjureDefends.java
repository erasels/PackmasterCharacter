package thePackmaster.cards.basicspack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.actions.basics.ConjureCardsAction;
import thePackmaster.cardmodifiers.basicspack.DuplicateModifier;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjureDefends extends AbstractPackmasterCard implements OnObtainCard {
    public final static String ID = makeID("ConjureDefends");

    public ConjureDefends() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.exhaust = true;
        this.cardsToPreview = new Defend();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse, card));
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new Defend(), Settings.HEIGHT / 2, Settings.HEIGHT / 2));
    }

    public void upp(){
        this.cardsToPreview.upgrade();
    }
}
