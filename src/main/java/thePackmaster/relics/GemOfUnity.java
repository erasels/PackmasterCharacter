package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.patches.CardParentPackPatch;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GemOfUnity extends AbstractPackmasterRelic {
    public static final String ID = makeID("GemOfUnity");

    private static ArrayList<AbstractCardPack> packsPlayed = new ArrayList<>();

    public GemOfUnity() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        packsPlayed = new ArrayList<>();
        resetCounter();
        this.description = getUpdatedDescription();
    }

    @Override
    public void onVictory() {
        resetCounter();
    }

    public void resetCounter(){
        if (AbstractDungeon.isPlayerInDungeon()) {
            packsPlayed.clear();
            counter = SpireAnniversary5Mod.PACKS_PER_RUN;
            if (AbstractDungeon.player.hasRelic(BanishingDecree.ID)) counter--;
            this.description = getUpdatedDescription();
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (CardParentPackPatch.parentPack.get(c)!= null){
            if (!packsPlayed.contains(CardParentPackPatch.parentPack.get(c))){
                counter--;
                packsPlayed.add(CardParentPackPatch.parentPack.get(c));
                if (counter == 0){
                    AbstractDungeon.player.heal(5);
                    addToBot(new GainBlockAction(AbstractDungeon.player, 20));
                    resetCounter();
                }
            }
        }
        this.description = getUpdatedDescription();
        super.onPlayCard(c, m);
    }

    public String getUpdatedDescription() {
        StringBuilder desc = new StringBuilder(DESCRIPTIONS[0]);
        if (AbstractDungeon.isPlayerInDungeon()) {
            if (packsPlayed.size() > 0) {
                desc.append(DESCRIPTIONS[1]);
                for (AbstractCardPack p : packsPlayed) {
                    desc.append(" NL ");
                    desc.append(p.name);
                }
            }
        }
        return desc.toString();
    }
}
