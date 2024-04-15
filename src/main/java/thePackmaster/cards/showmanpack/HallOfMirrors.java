package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.green.AfterImage;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.showmanpack.HallOfMirrorsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HallOfMirrors extends AbstractShowmanCard {
    public final static String ID = makeID("HallOfMirrors");

    public HallOfMirrors() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new AfterImage();
        this.isInnate = false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HallOfMirrorsPower(p, 1)));
        this.addToBot(new MakeTempCardInHandAction(new AfterImage()));
    }

    public void upp() {
        this.isInnate = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}