package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PoisonPotion;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.AllSeeingPower;
import thePackmaster.powers.legacypack.PoisonMasteryPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AllSeeing extends AbstractPackmasterCard {
    public final static String ID = makeID("AllSeeing");


    public AllSeeing() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new AllSeeingPower(p, magicNumber, 3));
    }

    public void upp() {
            upgradeMagicNumber(2);
    }
}