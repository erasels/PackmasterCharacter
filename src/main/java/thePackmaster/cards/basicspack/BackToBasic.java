package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.blue.Zap;
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
import thePackmaster.actions.transmutationpack.DrawFilteredCardsAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BackToBasic extends AbstractPackmasterCard {
    public final static String ID = makeID("BackToBasic");

    public BackToBasic() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 10;
        this.baseSecondMagic = this.secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, Wiz.hand().size(), true, false));
        addToBot(new DrawFilteredCardsAction(magicNumber, (c) -> c.rarity == CardRarity.BASIC));
        addToBot(new GainEnergyAction(secondMagic));
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}
