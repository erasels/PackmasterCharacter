package thePackmaster.cards.creativitypack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BarrierBeacon extends AbstractCreativityCard {

    public final static String ID = makeID(BarrierBeacon.class.getSimpleName());

    public BarrierBeacon() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 7;
        exhaust = true;
    }

    @Override
    public void upp() {
        upgradeBlock(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(p, p, block));
        AbstractCard dupe = makeCopy();
        if (upgraded) dupe.upgrade();
        addToBot(new MakeTempCardInHandAction(dupe));
    }
}
