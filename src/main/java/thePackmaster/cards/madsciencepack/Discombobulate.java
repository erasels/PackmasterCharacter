package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.SpireAnniversary5Mod.ISCARDMODIFIED;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Discombobulate extends AbstractMadScienceCard {
    public final static String ID = makeID("Discombobulate");

    public Discombobulate() {
        super(ID, 1,  CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 7;
        this.isMultiDamage = true;

    }

    @Override
    public void triggerOnGlowCheck() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(ISCARDMODIFIED)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    public void use(AbstractPlayer p, AbstractMonster m)
    {
        allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(ISCARDMODIFIED)){
                allDmg(AbstractGameAction.AttackEffect.BLUNT_LIGHT);
                return;
            }
        }

    }

    public void upp() {
        upgradeDamage(3);
    }
}