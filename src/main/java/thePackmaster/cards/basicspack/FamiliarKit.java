package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.blue.Zap;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.cards.green.Survivor;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FamiliarKit extends AbstractMultipleCardPreviewCard implements OnObtainCard {
    public final static String ID = makeID("FamiliarKit");

    public FamiliarKit() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 2;
        this.baseSecondMagic = this.secondMagic = 1;
        this.cardToPreview.add(new Bash());
        this.cardToPreview.add(new Survivor());
        this.cardToPreview.add(new Zap());
    }

    @Override
    public void onObtainCard() {
        for(int i = 0; i<this.cardToPreview.size(); i++){
            float width = (i+1) * Settings.WIDTH / 4.0F;
            AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(cardToPreview.get(i), width, Settings.HEIGHT / 2));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new FocusPower(p, this.secondMagic), this.secondMagic));
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}
