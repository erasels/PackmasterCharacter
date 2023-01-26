package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cardmodifiers.gemspack.TempStrengthGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TempStrengthGem extends AbstractGemsCard {
    public final static String ID = makeID("TempStrengthGem");

    public TempStrengthGem() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
    }

    @Override
    public AbstractCardModifier myMod() {
        return new TempStrengthGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(Wiz.p(), Wiz.p(), new StrengthPower(Wiz.p(), 2), 2));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(Wiz.p(), Wiz.p(), new LoseStrengthPower(Wiz.p(), 2), 2));

    }

 
}