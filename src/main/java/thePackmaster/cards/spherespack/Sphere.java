package thePackmaster.cards.spherespack;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Dark;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.downfallpack.Ghostflame;
import thePackmaster.orbs.spherespack.Blaze;
import thePackmaster.orbs.spherespack.Polar;

public class Sphere extends AbstractSpheresCard implements CustomSavable<Integer> {
    public static final String ID = SpireAnniversary5Mod.makeID("Sphere");
    private static final int COST = 1;
    private static final int BLOCK = 5;
    private static final int UPGRADE_BLOCK = 3;

    private SphereOrb orb;

    public Sphere() {
        this(!CardCrawlGame.isInARun() || AbstractDungeon.miscRng == null ? null : intToOrb(AbstractDungeon.miscRng.random(SphereOrb.values().length - 1)));
    }

    public Sphere (SphereOrb orb) {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.orb = orb;
        this.baseBlock = BLOCK;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.updateNameAndDescription();
    }

    @Override
    public void upp() {
        this.upgradeBlock(UPGRADE_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, this.block));
        AbstractOrb orbToChannel = getOrb(this.orb);
        if (orbToChannel != null) {
            this.addToBot(new ChannelAction(orbToChannel));
        }
    }

    private void updateNameAndDescription() {
        if (this.orb != null) {
            String orbName = getOrb(orb).name;
            this.name = cardStrings.EXTENDED_DESCRIPTION[0].replace("{0}", orbName);
            String orbNameForDescription = ((this.orb == SphereOrb.Blaze || this.orb == SphereOrb.Polar || this.orb == SphereOrb.Ghostflame) ? SpireAnniversary5Mod.modID + ":" : "") + orbName;
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[1].replace("{0}", orbNameForDescription);
            this.initializeTitle();
            this.initializeDescription();
        }
    }

    private static AbstractOrb getOrb(SphereOrb orb) {
        switch (orb) {
            case Lightning:
                return new Lightning();
            case Dark:
                return new Dark();
            case Ghostflame:
                return new Ghostflame();
            case Blaze:
                return new Blaze();
            case Polar:
                return new Polar();
            default:
                return null;
        }
    }

    private static SphereOrb intToOrb(int n) {
        switch (n) {
            case 0:
                return SphereOrb.Lightning;
            case 1:
                return SphereOrb.Dark;
            case 2:
                return SphereOrb.Ghostflame;
            case 3:
                return SphereOrb.Blaze;
            case 4:
                return SphereOrb.Polar;
            default:
                return null;
        }
    }

    private static Integer orbToInt(SphereOrb orb) {
        switch (orb) {
            case Lightning:
                return 0;
            case Dark:
                return 1;
            case Ghostflame:
                return 2;
            case Blaze:
                return 3;
            case Polar:
                return 4;
            default:
                return null;
        }
    }

    @Override
    public Integer onSave() {
        return orbToInt(this.orb);
    }

    @Override
    public void onLoad(Integer n) {
        this.orb = intToOrb(n);
        this.updateNameAndDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return this.orb == null ? new Sphere() : new Sphere(this.orb);
    }

    public enum SphereOrb {
        Lightning,
        Dark,
        Ghostflame,
        Blaze,
        Polar
    }
}
