package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SoilResiliency extends AbstractFarmerCard {
    public final static String ID = makeID("SoilResiliency");

    public SoilResiliency() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 1;
        this.exhaust = true;
    }


    public void triggerOnGlowCheck() {
        int count = checkTypes(false);
        if (count >= 3) {this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();}
        else{this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();}
        }



    public void use(AbstractPlayer p, AbstractMonster m) {
        this.exhaust = true;
        atb(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        atb(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        int count = checkTypes(true);

        if(count >= 3){
            this.exhaust = false;
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
