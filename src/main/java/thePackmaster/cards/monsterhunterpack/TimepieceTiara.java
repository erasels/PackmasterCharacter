package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.monsterhunterpack.TimePiecePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TimepieceTiara extends AbstractMonsterHunterCard {
    public final static String ID = makeID("TimepieceTiara");

    public TimepieceTiara() {
        super(ID, 0, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        this.isInnate = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TimePiecePower(p, 3), 3));
    }

    public void upp() {
        this.isInnate = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}