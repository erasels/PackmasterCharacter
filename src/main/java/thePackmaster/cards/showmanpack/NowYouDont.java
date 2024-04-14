package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.showmanpack.NowYouDontPower;

import static thePackmaster.SpireAnniversary5Mod.addPotions;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NowYouDont extends AbstractShowmanCard {
    public final static String ID = makeID("NowYouDont");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public NowYouDont() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
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