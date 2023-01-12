package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.powers.rippack.DividedFuryPower;
import thePackmaster.vfx.rippack.FlimsyBashEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;
import static thePackmaster.util.Wiz.att;

public class FlimsyBash extends AbstractRippableCard {
    public final static String ID = makeID("FlimsyBash");

    public FlimsyBash() {
        this(null, null);
    }

    public FlimsyBash(AbstractRippedArtCard artCard, AbstractRippedTextCard textCard) {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 2;
        if (artCard == null && textCard == null) {
            setRippedCards(new FlimsyBashArt(this), new FlimsyBashText(this));
        } else if(artCard == null){
            setRippedCards(new FlimsyBashArt(this), textCard);
        } else {
            setRippedCards(artCard, new FlimsyBashText(this));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractGameEffect yeet = FlimsyBashEffect.ChuckIt(m);
        atb(new VFXAction(yeet));
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                if (yeet.isDone) {
                    isDone = true;
                }
            }
        });
        atb(new VFXAction(FlimsyBashEffect.Drop(m)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }
}
