package thePackmaster.potions;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.BoosterTutorAction;


public class ModdersDelight extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("ModdersDelight");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public ModdersDelight() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SNECKO, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.TAN.cpy();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }


    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            this.addToBot(new EmptyDeckShuffleAction());
            this.addToBot(new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }

        addToBot(new BoosterTutorAction(potency));
    }

    public CustomPotion makeCopy() {
        return new ModdersDelight();
    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }
}


