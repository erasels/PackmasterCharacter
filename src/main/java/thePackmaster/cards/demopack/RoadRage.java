/*package thePackmaster.cards.demopack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.LambdaPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class RoadRage extends AbstractPackmasterCard {
    public final static String ID = makeID("RoadRage");
    // intellij stuff skill, self, rare, , , , , , 

    public RoadRage() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        applyToSelf(new LambdaPower(makeID("RoadRagePower"), cardStrings.EXTENDED_DESCRIPTION[0], AbstractPower.PowerType.DEBUFF, false, p, -1, false) {
            @Override
            public void onUseCard(AbstractCard card, UseCardAction action) {
                flash();
                atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, BaseMod.MAX_HAND_SIZE, true));
                atb(new RemoveSpecificPowerAction(owner, owner, this));
            }

            @Override
            public void updateDescription() {
                description = cardStrings.EXTENDED_DESCRIPTION[1];
            }
        });
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}

 */