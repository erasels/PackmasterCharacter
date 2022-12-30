package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PoisonPotion;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.legacypack.PoisonMasteryPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PoisonMastery extends AbstractPackmasterCard {
    public final static String ID = makeID("PoisonMastery");


    public PoisonMastery() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new ObtainPotionAction(new PoisonPotion()));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PoisonMasteryPower(m, this.magicNumber), this.magicNumber));
    }

    public void upp() {
            upgradeMagicNumber(1);
    }
}