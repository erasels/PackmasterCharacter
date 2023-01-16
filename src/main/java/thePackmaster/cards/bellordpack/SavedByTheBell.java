package thePackmaster.cards.bellordpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class SavedByTheBell extends AbstractBellordCard implements OnObtainCard {
    public final static String ID = makeID("SavedByTheBell");
    // intellij stuff skill, self, rare, , , , , , 

    public SavedByTheBell() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("BELL"));
        applyToSelf(new IntangiblePlayerPower(p, 1));
    }

    @Override
    public void onObtainCard() {
        float fractical = Settings.WIDTH / 4;
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), fractical, Settings.HEIGHT / 2));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), fractical * 2, Settings.HEIGHT / 2));
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), fractical * 3, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}