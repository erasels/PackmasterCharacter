package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.doDmg;

public class MasterSpark extends AbstractPackmasterCard implements AmplifyCard{
    public final static String ID = makeID(MasterSpark.class.getSimpleName());
    private static final int DMG = 9, UPG_DMG = 1;
    private static final int MAGIC = 16, UPG_MAGIC = 3;

    public MasterSpark() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseSecondDamage = secondDamage = MAGIC;

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MindblastEffect(p.hb.cX, p.hb.cY, p.flipHorizontal), 0.1F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
        upgradeSecondDamage(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return true;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MindblastEffect(p.hb.cX, p.hb.cY, p.flipHorizontal), 0.1F);
        doDmg(m, secondDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }
}
