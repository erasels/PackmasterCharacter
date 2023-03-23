package thePackmaster.cards.colorlesspack;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.cards.colorless.Enlightenment;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.colorlesspack.MonasteryPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class VisitAMonastery extends AbstractColorlessPackCard {
    public final static String ID = makeID("VisitAMonastery");
    // intellij stuff power, self, rare, , , , , , 

    public VisitAMonastery() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        MultiCardPreview.add(this, new Madness(), new Purity(), new Apotheosis());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MonasteryPower());
    }

    public void upp() {
        isInnate = true;
    }
}