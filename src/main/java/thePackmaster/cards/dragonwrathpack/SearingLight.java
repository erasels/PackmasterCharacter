package thePackmaster.cards.dragonwrathpack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.red.FlameBarrier;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.orbs.dragonwrathpack.LightOrb;

import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SearingLight extends AbstractDragonwrathCard {

    public static final String ID = makeID(SearingLight.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");


    public SearingLight(){
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
        blck();
        addToBot(new ChannelAction(new LightOrb()));
    }

    @Override
    public String cardArtCopy() {
        return FlameBarrier.ID;
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeBlock(3);
    }
}

