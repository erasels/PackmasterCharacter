package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.distortionpack.HPLostTrackingPatch;
import thePackmaster.vfx.distortionpack.StaticEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Shatter extends AbstractDistortionCard {
    public final static String ID = makeID("Shatter");
    // intellij stuff attack, enemy, rare, 4, 2, , , ,

    public Shatter() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void applyPowers() {
        super.applyPowers();

        if (!this.rawDescription.equals(cardStrings.DESCRIPTION)) {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);

        int hits = HPLostTrackingPatch.Field.hpLostTimes.get(mo);
        if (hits > 0 && !this.rawDescription.equals(cardStrings.EXTENDED_DESCRIPTION[0])) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            this.magicNumber = this.baseMagicNumber = hits;
            initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            for (int i = 0; i < HPLostTrackingPatch.Field.hpLostTimes.get(m); ++i) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                atb(new VFXAction(new StaticEffect(m, 8)));
            }
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void upp() {
        upgradeDamage(2);
    }
}