package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.warlockpack.Imp;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class TillTheEarth extends AbstractFarmerCard {
    public final static String ID = makeID("TillTheEarth");

    public TillTheEarth() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 7;
        cardsToPreview = new Fertilizer();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new MakeTempCardInHandAction(new Fertilizer()));
    }

    public void upp() {
        upgradeDamage(3);
    }
}
