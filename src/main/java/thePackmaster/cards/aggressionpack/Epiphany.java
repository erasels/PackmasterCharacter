package thePackmaster.cards.aggressionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.stances.aggressionpack.AggressionStance;

public class Epiphany extends AbstractAggressionCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Epiphany");
    private static final int COST = 1;
    private static final int DRAW = 3;
    private static final int UPGRADE_DRAW = 1;

    public Epiphany() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.player.stance != null && !AbstractDungeon.player.stance.ID.equals(NeutralStance.STANCE_ID)) {
                    this.addToTop(new DrawCardAction(magicNumber));
                    this.addToBot(new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F));
                    this.addToTop(new ChangeStanceAction(new NeutralStance()));
                }
                else {
                    this.addToTop(new ChangeStanceAction(new AggressionStance()));
                }
                
                this.isDone = true;
            }
        });
    }
}
