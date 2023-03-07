package thePackmaster.cards.spherespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.SpireAnniversary5Mod;

public class FrostConversion extends AbstractSpheresCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FrostConversion");
    private static final int COST = 1;

    public FrostConversion() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                this.addToTop(new ChannelAction(new Frost()));
                if (checkUniqueOrbs()) {
                    this.addToTop(new ChannelAction(new Frost()));
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
        return AbstractDungeon.player.orbs.stream().filter(o -> !(o instanceof EmptyOrbSlot)).map(o -> o.ID).distinct().count() >= 3;
    }
}
