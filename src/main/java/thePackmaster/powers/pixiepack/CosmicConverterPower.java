package thePackmaster.powers.pixiepack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

public class CosmicConverterPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CosmicConverterPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CosmicConverterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (int i = 0; i < amount; i++) {
            AbstractCard lastPlayed = null;
            for (int j = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; j > 0; j--) {
                if (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j).type != AbstractCard.CardType.CURSE && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j).type != AbstractCard.CardType.STATUS) {
                    lastPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j);
                    break;
                }
            }
            AbstractCard toAdd = PixiePack.pixieGenerate(lastPlayed.costForTurn, null, lastPlayed.type, null);
            if (toAdd == null) toAdd = new Madness();
            CardModifierManager.addModifier(toAdd, new EtherealMod());
            addToBot(new MakeTempCardInHandAction(toAdd));
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        updateDescription();
    }

    @Override
    public void updateDescription() {
        AbstractCard lastPlayed = null;
        for (int j = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 1; j > 0; j--) {
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j).type != AbstractCard.CardType.CURSE && AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j).type != AbstractCard.CardType.STATUS) {
                lastPlayed = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(j);
                break;
            }
        }
        if (lastPlayed == null) return;
        switch (lastPlayed.costForTurn)
        {
            default:
                this.description = DESCRIPTIONS[0] + " NL #b" + lastPlayed.costForTurn + DESCRIPTIONS[1];
                break;
            case -1:
                this.description = DESCRIPTIONS[0] + " NL #b" + DESCRIPTIONS[6] + DESCRIPTIONS[1];
                break;
            case -2:
                this.description = DESCRIPTIONS[0] + " NL #b" + DESCRIPTIONS[7];
                break;
        }
        switch (lastPlayed.type) {
            case ATTACK:
                this.description += DESCRIPTIONS[2] + DESCRIPTIONS[5];
                break;
            case SKILL:
                this.description += DESCRIPTIONS[3] + DESCRIPTIONS[5];
                break;
            case POWER:
                this.description +=  DESCRIPTIONS[4] + DESCRIPTIONS[5];
                break;
        }
    }
}
