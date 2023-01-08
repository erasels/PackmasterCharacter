package thePackmaster.powers.prismaticpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.Plasma;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuirionDryadPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("QuirionDryad");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private List<AbstractCard.CardColor> colors;

    public QuirionDryadPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
        this.colors = new ArrayList<>();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (this.owner.isPlayer) {
            long plasmaOrbs = AbstractDungeon.player.orbs.stream().filter(o -> o instanceof Plasma).count();
            if (plasmaOrbs > 0) {
                this.flashWithoutSound();
                this.addToBot(new GainBlockAction(this.owner, this.amount));
            }
        }
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!this.colors.contains(card.color)) {
            this.colors.add(card.color);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
            this.updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0].replace("{0}", this.amount + "").replace("{1}", this.getPlayedColors());
    }

    private String getPlayedColors() {
        return this.colors == null || this.colors.isEmpty()
                ? DESCRIPTIONS[1]
                : this.colors.stream().map(this::getColorName).collect(Collectors.joining(", "));
    }

    private String getColorName(AbstractCard.CardColor color) {
        if (color == ThePackmaster.Enums.PACKMASTER_RAINBOW) {
            return DESCRIPTIONS[8];
        }
        switch (color) {
            case RED:
                return DESCRIPTIONS[2];
            case GREEN:
                return DESCRIPTIONS[3];
            case BLUE:
                return DESCRIPTIONS[4];
            case PURPLE:
                return DESCRIPTIONS[5];
            case COLORLESS:
                return DESCRIPTIONS[6];
            case CURSE:
                return DESCRIPTIONS[7];
            default:
                return color.name();
        }
    }
}
