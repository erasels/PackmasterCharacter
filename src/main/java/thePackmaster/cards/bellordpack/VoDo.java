package thePackmaster.cards.bellordpack;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class VoDo extends AbstractBellordCard implements OnObtainCard {
    public final static String ID = makeID("VoDo");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public VoDo() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MathUtils.randomBoolean())
            atb(new SFXAction("BELL"));
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToEnemy(m, new StrengthPower(m, -2));
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}