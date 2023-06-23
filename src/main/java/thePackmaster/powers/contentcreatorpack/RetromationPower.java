package thePackmaster.powers.contentcreatorpack;


import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.GemsPack;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.makeInHand;

public class RetromationPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("RetromationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private List<AbstractCard> potentialCards = new ArrayList<>();

    public RetromationPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        potentialCards = this.getPotentialCards();
    }

    private List<AbstractCard> getPotentialCards() {
        return CardLibrary.getAllCards().stream()
                .filter(c -> c.color == ThePackmaster.Enums.PACKMASTER_RAINBOW)
                .filter(c -> c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE)
                .filter(c -> !c.hasTag(AbstractCard.CardTags.HEALING))
                .filter(c -> (AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER && SpireAnniversary5Mod.allPacksMode) || !SpireAnniversary5Mod.currentPoolPacks.stream().map(p -> p.packID).collect(Collectors.toList()).contains(SpireAnniversary5Mod.cardParentMap.getOrDefault(c.cardID, null)))
                .filter(c -> !GemsPack.ID.equals(SpireAnniversary5Mod.cardParentMap.getOrDefault(c.cardID, null)))
                .collect(Collectors.toList());
    }

    public void atStartOfTurnPostDraw() {
        if (!potentialCards.isEmpty()) {
            this.flash();
            for (int i = 0; i < amount; i++) {
                AbstractCard c = Wiz.getRandomItem(potentialCards, AbstractDungeon.cardRandomRng).makeCopy();
                CardModifierManager.addModifier(c, new EtherealMod());
                makeInHand(c);
            }
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        sb.append(amount);
        sb.append(amount == 1 ? DESCRIPTIONS[1] : DESCRIPTIONS[2]);
        sb.append(DESCRIPTIONS[3]);
        sb.append(amount == 1 ? DESCRIPTIONS[4] : DESCRIPTIONS[5]);
        description = sb.toString();
    }
}
