package thePackmaster.cards.marisapack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RisingStrike extends AbstractPackmasterCard implements StartupCard {
    public final static String ID = makeID(RisingStrike.class.getSimpleName());
    private static final int DMG = 4, UPG_DMG = 2;
    private static final int MAGIC = 2, UPG_MAGIC = 1;

    public RisingStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
        //cardsToPreview = new Spark();

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new PlasmaOrbActivateEffect(m.hb.cX, m.hb.cY), Settings.ACTION_DUR_FAST);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new ChargeUpPower(magicNumber));
        //Wiz.makeInHand(cardsToPreview);
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        Wiz.applyToSelf(new ChargeUpPower(magicNumber));
        return false;
    }
}
