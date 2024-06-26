package thePackmaster.cards.marisapack.deprecated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;
import org.apache.commons.lang3.math.NumberUtils;
import thePackmaster.cards.marisapack.AbstractMarisaCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
public class DragonMeteor extends AbstractMarisaCard {
    public final static String ID = makeID(DragonMeteor.class.getSimpleName());
    private static final int DMG = 6;
    private static final int MAGIC = 2, UPG_MAGIC = 1;

    public DragonMeteor() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new PlasmaOrbActivateEffect(m.hb.cX, m.hb.cY), Settings.ACTION_DUR_XFAST);
        Wiz.vfx(new SearingBlowEffect(m.hb.cX, m.hb.cY, NumberUtils.max((damage / MAGIC) - DMG, 0)), 0.2F);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    @Override
    public void applyPowers() {
        int tmp = baseDamage;
        baseDamage += Wiz.p().exhaustPile.size() * magicNumber;
        super.applyPowers();
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int tmp = baseDamage;
        baseDamage += Wiz.p().exhaustPile.size() * magicNumber;
        super.calculateCardDamage(mo);
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
}
