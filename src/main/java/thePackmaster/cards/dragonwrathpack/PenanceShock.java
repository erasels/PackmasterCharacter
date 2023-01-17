package thePackmaster.cards.dragonwrathpack;


import basemod.devcommands.power.Power;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.BallLightning;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.dragonwrathpack.SmiteAction;

import thePackmaster.powers.dragonwrathpack.PenancePower;


import static thePackmaster.SpireAnniversary5Mod.makeCardPath;
import static thePackmaster.SpireAnniversary5Mod.makeID;
public class PenanceShock extends AbstractDragonwrathCard {

    public static final String ID = makeID(PenanceShock.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("DivineJolt.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATIO

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;  // UPGRADE_PLUS_DMG = 2

    // /STAT DECLARATION/


    public PenanceShock(){
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
    baseDamage =DAMAGE;
    this.magicNumber = baseMagicNumber = 2;
    secondMagic = baseSecondMagic = 1;
}


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < secondMagic; ++i) {
           m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
           if (m != null) {
               addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
               addToBot(new ApplyPowerAction(m,p,new PenancePower(m,p,magicNumber)));
           } else {
               m = AbstractDungeon.getMonsters().getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
               addToBot(new SmiteAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
               addToBot(new ApplyPowerAction(m,p,new PenancePower(m,p,magicNumber)));
           }

        }
        addToBot(new ChannelAction(new Lightning()));
    }

    @Override
    public String cardArtCopy() {
        return BallLightning.ID;
    }

    // Upgraded stats.
    @Override
    public void upp() {
        upgradeSecondMagic(1);
    }
}