package thePackmaster.cards.coresetpack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class MayhemForm extends AbstractPackmasterCard {
    public final static String ID = makeID("MayhemForm");

    public MayhemForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
   }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO
    }

    @Override
    public void upp() {
    }


}


