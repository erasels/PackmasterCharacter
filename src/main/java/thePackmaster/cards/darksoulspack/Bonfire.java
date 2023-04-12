package thePackmaster.cards.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SanctityEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Bonfire extends AbstractDarkSoulsCard {
    public final static String ID = makeID("Bonfire");
    // intellij stuff skill, self, rare, , , , , 30, 

    public Bonfire() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 30;
        baseSecondMagic = secondMagic = 30;
        isUnnate=true;
        exhaust=true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AddTemporaryHPAction(p, p, magicNumber));
        for (AbstractMonster XD : Wiz.getEnemies()) {
            addToBot(new VFXAction(new SanctityEffect(XD.hb.cX, XD.hb.cY), 0.1F));
            addToBot(new HealAction(XD, Wiz.p(), this.secondMagic));
        }
    }

    public void upp() {
        this.isUnnate=false;
    }



}