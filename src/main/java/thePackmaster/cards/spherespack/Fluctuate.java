package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Collections;

public class Fluctuate extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Fluctuate");
    private static final int COST = 1;
    private static final int AMOUNT = 1;
    private static final int EXTRA_AMOUNT = 1;
    private static final int UPGRADE_EXTRA_AMOUNT = 1;

    public Fluctuate() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = EXTRA_AMOUNT;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_EXTRA_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int amount = AMOUNT;
                if (checkUniqueOrbs()) {
                    amount += Fluctuate.this.magicNumber;
                }
                ArrayList<AbstractMonster> monsters = Wiz.getEnemies();
                Collections.reverse(monsters);
                for (AbstractMonster m : monsters) {
                    Wiz.applyToEnemyTop(m, new WeakPower(m, amount, false));
                    Wiz.applyToEnemyTop(m, new VulnerablePower(m, amount, false));
                }
                this.isDone = true;
            }
        });
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (checkUniqueOrbs()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    private static boolean checkUniqueOrbs() {
        return AbstractDungeon.player.orbs.stream().filter(o -> !(o instanceof EmptyOrbSlot)).map(o -> o.ID).distinct().count() > 3;
    }
}
