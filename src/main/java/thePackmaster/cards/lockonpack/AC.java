package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cards.lockonpack.special.DC;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AC extends AbstractLockonCard {

    public final static String ID = makeID(AC.class.getSimpleName());

    public AC() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 10;
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doBlk(block);
        AbstractCard dc = new DC();
        if (upgraded) dc.upgrade();
        Wiz.makeInHand(dc);
    }
}
