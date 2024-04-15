package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.effects.showmanpack.SmallSpotlightEffect;
import thePackmaster.powers.showmanpack.NowYouDontPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SeeMe extends AbstractShowmanCard {
    public final static String ID = makeID("SeeMe");

    public SeeMe() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        this.cardsToPreview = new NowYouDont();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new SmallSpotlightEffect()));
        this.addToBot(new ApplyPowerAction(p, p, new VigorPower(p, magicNumber), magicNumber));
        AbstractCard dont = new NowYouDont();
        if (upgraded){
            dont.upgrade();
        }
        this.addToBot(new ApplyPowerAction(p, p, new NowYouDontPower(p, 1, dont), 1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        this.cardsToPreview.upgrade();
    }
}