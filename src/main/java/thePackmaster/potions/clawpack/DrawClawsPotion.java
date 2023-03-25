package thePackmaster.potions.clawpack;


import basemod.abstracts.CustomPotion;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;


public class DrawClawsPotion extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("DrawClawsPotion");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public DrawClawsPotion() {
        super(NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.H, PotionColor.NONE);
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

        addToBot(new DrawCardAction(potency, new AbstractGameAction() {
            @Override
            public void update() {
                DrawCardAction.drawnCards.stream().filter(c -> c.hasTag(SpireAnniversary5Mod.CLAW)).forEach(c -> Wiz.atb(new DrawCardAction(1)));
                isDone = true;
            }
        }));
    }

    public CustomPotion makeCopy() {
        return new DrawClawsPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }
}


