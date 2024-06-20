package thePackmaster.cards.rippack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.rippack.RippableModifier;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makeImagePath;
import static thePackmaster.util.Wiz.atb;

public class SurprisePack extends AbstractRipCard implements OnRipInterface {
    public final static String ID = makeID("SurprisePack");


    public SurprisePack() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
        CardModifierManager.addModifier(this, new RippableModifier());
    }

    @Override
    protected Texture getPortraitImage() {
        if (upgraded) {
            return ImageMaster.loadImage(makeImagePath("cards/SurprisePack_u_p.png"));
        }
        return super.getPortraitImage();
    }

    @Override
    public void upp() {
        upgradeSecondMagic(1);
        loadCardImage(makeImagePath("cards/SurprisePack_u.png"));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void onRip() {
        atb(new GainEnergyAction(secondMagic));
    }
}
