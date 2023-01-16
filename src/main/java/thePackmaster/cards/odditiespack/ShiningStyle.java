package thePackmaster.cards.odditiespack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class ShiningStyle extends AbstractPackmasterCard implements StartupCard {
    public final static String ID = makeID("ShiningStyle");
    // intellij stuff power, self, uncommon, , , , , 1, 

    public ShiningStyle() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new DexterityPower(p, magicNumber));
    }

    public void upp() {
        upgradeSecondMagic(5);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.secondMagic, true));
        atb(new GainGoldAction(this.secondMagic));
        return true;
    }
}