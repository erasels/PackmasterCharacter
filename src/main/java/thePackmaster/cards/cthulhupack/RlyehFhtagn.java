package thePackmaster.cards.cthulhupack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.cthulhupack.NamelessMistPower;
import thePackmaster.powers.cthulhupack.NextTurnGainMadnessPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RlyehFhtagn extends AbstractPackmasterCard {
    public final static String ID = makeID("RlyehFhtagn");

    public RlyehFhtagn() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Madness();

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new NextTurnGainMadnessPower(p, 3, upgraded));
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new PressEndTurnButtonAction());
    }

    public void upp() {
        cardsToPreview.upgrade();
    }
}