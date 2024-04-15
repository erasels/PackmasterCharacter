package thePackmaster.cards.showmanpack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NowYouDont extends AbstractPackmasterCard {
    public final static String ID = makeID("NowYouDont");

    public NowYouDont() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseBlock = block = 16;
        this.isEthereal = true;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0 ; i<25; i++) {
            addToBot(new VFXAction(new ExhaustBlurEffect(p.hb.cX + Settings.scale * ((float) Math.random() * 160f) - 80f, p.hb.cY + Settings.scale * ((float) Math.random() * 160f) - 80f)));
        }
    }

    public void upp() {
        upgradeBlock(4);
    }
}