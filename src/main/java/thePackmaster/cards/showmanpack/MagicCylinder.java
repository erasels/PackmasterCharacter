package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.showmanpack.HallOfMirrorsPower;
import thePackmaster.powers.showmanpack.MagicCylinderPower;
import thePackmaster.powers.showmanpack.NextTrickPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MagicCylinder extends AbstractShowmanCard {
    public final static String ID = makeID("MagicCylinder");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MagicCylinder() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MagicCylinderPower(p, 1)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}