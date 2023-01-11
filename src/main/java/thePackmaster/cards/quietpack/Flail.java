package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Flail extends AbstractPackmasterCard {
    public final static String ID = makeID("Flail");

    public Flail() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 13;
        cardsToPreview = new Weight();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new FlashAtkImgEffect(m.hb.cX-30.0f, m.hb.cY-30.0f, AbstractGameAction.AttackEffect.BLUNT_HEAVY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        this.addToBot(new MakeTempCardInHandAction(cardsToPreview.makeCopy(),2));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}


