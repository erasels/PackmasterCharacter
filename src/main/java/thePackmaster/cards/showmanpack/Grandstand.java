package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedBluePower;
import thePackmaster.effects.showmanpack.SmallSpotlightEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Grandstand extends AbstractShowmanCard {
    public final static String ID = makeID("Grandstand");

    public Grandstand() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new ApplyPowerAction(p, p, new EnergizedBluePower(p, magicNumber), magicNumber));
        this.addToBot(new VFXAction(new SmallSpotlightEffect()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}