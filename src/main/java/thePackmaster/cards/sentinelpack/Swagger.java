package thePackmaster.cards.sentinelpack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.sentinelpack.SwaggerPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Swagger extends AbstractSentinelCard {
    public final static String ID = makeID("Swagger");

    public Swagger() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SwaggerPower(Wiz.p(), magicNumber));
    }

    @Override
    public void upp() {
        isInnate = true;
    }

}


