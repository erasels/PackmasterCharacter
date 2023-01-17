package thePackmaster.powers.contentcreatorpack;


import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.allPacks;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.makeInHand;

public class RetromationPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("RetromationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private ArrayList<AbstractCardPack> potentialPacks = new ArrayList<>();

    public RetromationPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
        for (AbstractCardPack p : allPacks) {
            if (!SpireAnniversary5Mod.currentPoolPacks.contains(p)) { // TODO: Check that this works
                potentialPacks.add(p);
            }
        }
    }

    public void atStartOfTurnPostDraw() {
        if (!potentialPacks.isEmpty()) {
            this.flash();
            AbstractCard q = CardLibrary.getCard(Wiz.getRandomItem(Wiz.getRandomItem(potentialPacks, AbstractDungeon.cardRandomRng).getCards(), AbstractDungeon.cardRandomRng));
            CardModifierManager.addModifier(q, new EtherealMod());
            makeInHand(q);
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
