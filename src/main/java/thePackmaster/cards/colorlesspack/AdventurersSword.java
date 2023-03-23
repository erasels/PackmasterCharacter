package thePackmaster.cards.colorlesspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.shuffleIn;

public class AdventurersSword extends AbstractColorlessPackCard {
    public final static String ID = makeID("AdventurersSword");
    // intellij stuff attack, enemy, uncommon, 11, 4, , , , 

    public AdventurersSword() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 11;
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new Triforce();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        shuffleIn(new Triforce());
        if (AbstractDungeon.player.currentHealth == AbstractDungeon.player.maxHealth) {
            atb(new SFXAction("RELIC_DROP_MAGICAL"));
            atb(new LoseHPAction(m, p, magicNumber, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = AbstractDungeon.player.currentHealth == AbstractDungeon.player.maxHealth ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}