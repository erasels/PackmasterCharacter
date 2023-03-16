package thePackmaster.cards.coresetpack;


import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.coresetpack.MayhemFormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;


public class MayhemForm extends AbstractPackmasterCard {
    public final static String ID = makeID("MayhemForm");

    public MayhemForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(BaseModCardTags.FORM);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MayhemFormPower(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }


}


