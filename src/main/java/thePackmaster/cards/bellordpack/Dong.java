package thePackmaster.cards.bellordpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class Dong extends AbstractBellordCard implements OnObtainCard {
    public final static String ID = makeID("Dong");
    // intellij stuff attack, enemy, uncommon, 20, 4, , , 2, 1

    public Dong() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 20;
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ViceCrushEffect(m.hb.cX, m.hb.cY)));
        atb(new SFXAction("BELL"));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void onObtainCard() {
        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}