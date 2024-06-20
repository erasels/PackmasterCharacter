package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BaalorBash extends AbstractContentCard {
    public final static String ID = makeID("BaalorBash");

    public BaalorBash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 13;
        exhaust = true;
        isUnnate = true;
        cardsToPreview = new BaalorBlueprint();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new MakeTempCardInDiscardAction(new BaalorBlueprint(), 1));
    }

    public void upp() {
        isUnnate = false;
    }
}