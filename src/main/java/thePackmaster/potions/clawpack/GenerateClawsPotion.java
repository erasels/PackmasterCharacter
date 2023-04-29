package thePackmaster.potions.clawpack;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.blue.Claw;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.clawpack.GhostClaw;
import thePackmaster.util.Wiz;


public class GenerateClawsPotion extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("GenerateClawsPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public GenerateClawsPotion() {
        super(NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.BOLT, PotionColor.NONE);
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
        for (int i = 0; i < potency; i++) {
            Wiz.atb(new MakeTempCardInHandAction(new Claw()));
        }
    }

    public CustomPotion makeCopy() {
        return new GenerateClawsPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }
}


