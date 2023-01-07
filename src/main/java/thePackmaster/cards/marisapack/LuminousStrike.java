package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.CasualFlameParticleEffect;
import thePackmaster.vfx.marisapack.FireIgniteEffect;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LuminousStrike extends AbstractPackmasterCard implements AmplifyCard{
    public final static String ID = makeID(LuminousStrike.class.getSimpleName());
    private static final int DMG = 6, UPG_DMG = 1;
    private static final int MAGIC = 3, UPG_MAGIC = 1;

    public LuminousStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (int i = 0; i < damage/2; i++) {
            Wiz.vfx(new StarBounceEffect(m.hb.cX, m.hb.cY));
        }
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new FireIgniteEffect(m.hb.cX, m.hb.cY, damage));
        Wiz.vfx(new CasualFlameParticleEffect(m.hb.cX, m.hb.cY));
    }

    @Override
    public int getAmplifyCost() {
        return 2;
    }

    @Override
    public void applyPowers() {
        boolean amp = shouldAmplify(this);
        int bd = baseDamage;
        if(amp) {
            baseDamage = baseDamage + EnergyPanel.totalCount * magicNumber;
        }
        super.applyPowers();
        if(amp) isDamageModified = true;
        baseDamage = bd;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        boolean amp = shouldAmplify(this);
        int bd = baseDamage;
        if(amp) {
            baseDamage = baseDamage + EnergyPanel.totalCount * magicNumber;
        }
        super.calculateCardDamage(mo);
        if(amp) isDamageModified = true;
        baseDamage = bd;
    }
}
