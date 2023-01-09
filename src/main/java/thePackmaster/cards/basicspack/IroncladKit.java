package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Bash;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IroncladKit extends AbstractKitCard{
    public final static String ID = makeID("IroncladKit");

    public IroncladKit() {
        super(ID, new Bash(), new Strike_Red());
    }
}
