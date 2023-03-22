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
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.actions.arcanapack.AllEnemyLoseHPAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class RingTheBell extends AbstractBellordCard implements OnObtainCard {
    public final static String ID = makeID("RingTheBell");
    // intellij stuff skill, all_enemy, uncommon, , , , , 13, 4

    public RingTheBell() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 13;
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MathUtils.randomBoolean())
            atb(new SFXAction("BELL"));
        atb(new AllEnemyLoseHPAction(p, magicNumber));
        applyToSelf(new DrawCardNextTurnPower(p, 1));
    }

    @Override
    public void onObtainCard() {
        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override //zhs card text thing
    public void initializeDescriptionCN() {
        super.initializeDescriptionCN();
        if (Settings.language == Settings.GameLanguage.ZHS && this.description!=null && this.description.size() >= 1 ) {
                this.description.remove(1);
        }
    }
}
