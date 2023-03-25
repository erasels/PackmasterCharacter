package thePackmaster.potions.clawpack;


import basemod.BaseMod;
import basemod.abstracts.CustomPotion;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.clawpack.AddClawTagAndMakeClawModifier;
import thePackmaster.cardmodifiers.clawpack.MakeClawModifier;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.atb;


public class AttackPotionButClaw extends CustomPotion {
    public static final String POTION_ID = SpireAnniversary5Mod.makeID("AttackPotionButClaw");
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;

    public AttackPotionButClaw() {
        super(NAME, POTION_ID, PotionRarity.PLACEHOLDER, PotionSize.CARD, PotionColor.NONE);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = Color.TAN.cpy();
        this.resetTips();
    }


    public void initializeData() {
        this.potency = getPotency();
        if (potency > 1) {
            this.description = potionStrings.DESCRIPTIONS[1] + potency + potionStrings.DESCRIPTIONS[2];
        } else {
            this.description = potionStrings.DESCRIPTIONS[0];
        }
        this.resetTips();
    }

    private void resetTips() {
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle("anniv5:modify")), BaseMod.getKeywordDescription("anniv5:modify")));
    }

    public void use(AbstractCreature target) {

        ArrayList<AbstractCard> generatedCards = new ArrayList<>();

        while (generatedCards.size() != 3) {
            boolean dupe = false;
            AbstractCard tmp = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK);

            for (AbstractCard c : generatedCards) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }

            if (!dupe) {
                generatedCards.add(tmp.makeCopy());
                if (tmp.hasTag(SpireAnniversary5Mod.CLAW)){
                    CardModifierManager.addModifier(generatedCards.get(generatedCards.size()-1), new MakeClawModifier(1));
                } else {
                    CardModifierManager.addModifier(generatedCards.get(generatedCards.size()-1), new AddClawTagAndMakeClawModifier(1));
                }
            }
        }

        atb(new SelectCardsAction(generatedCards, 1, DESCRIPTIONS[3], false,
                        abstractCard -> abstractCard.type == AbstractCard.CardType.ATTACK,

                (cards) -> {
                    for (AbstractCard c2 : cards) {
                        c2.setCostForTurn(0);
                        for (int i = 0; i < potency; i++) {
                            atb(new MakeTempCardInHandAction(c2.makeStatEquivalentCopy()));
                        }
                    }
                }
        ));

    }

    public CustomPotion makeCopy() {
        return new AttackPotionButClaw();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}


