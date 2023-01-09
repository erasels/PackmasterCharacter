package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.cards.blue.Dualcast;
import com.megacrit.cardcrawl.cards.blue.Zap;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DefectKit extends AbstractKitCard{
    public final static String ID = makeID("DefectKit");

    public DefectKit() {
        super(ID, new Zap(), new Dualcast());
    }
}
