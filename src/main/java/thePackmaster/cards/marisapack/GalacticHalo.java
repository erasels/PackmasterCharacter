package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BubbleEffect;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GalacticHalo extends AbstractPackmasterCard {
    public final static String ID = makeID(GalacticHalo.class.getSimpleName());
    private static final int BLK = 11, BLK_UPG = 3;
    private static final int MAGIC = 3, UPG_MAGIC = 1;

    public GalacticHalo() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLK;
        baseMagicNumber = magicNumber = MAGIC;

        setBackgroundTexture("anniv5Resources/images/512/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/marisapack/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new BubbleEffect(Color.SKY, ""), Settings.ACTION_DUR_FAST);
        blck();
        Wiz.applyToSelf(new ChargeUpPower(magicNumber));
    }

    public void upp() {
       upgradeBlock(BLK_UPG);
        upgradeMagicNumber(UPG_MAGIC);
    }
}
