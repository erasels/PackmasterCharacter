package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EmbraceHollowing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("EmbraceHollowing");
    // intellij stuff power, self, rare, , , , , 5, 3

    public EmbraceHollowing() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = Wiz.countDebuffs(p);
        if (this.upgraded){
            count++;
        }
        for (int i = 0; i < count; i++)
            Wiz.applyToSelf(new StrengthPower(p, 2));
    }

    public void upp() {
    }
}