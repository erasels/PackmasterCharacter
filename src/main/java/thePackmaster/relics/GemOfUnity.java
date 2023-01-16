package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GemOfUnity extends AbstractPackmasterRelic {
    public static final String ID = makeID(GemOfUnity.class.getSimpleName());
    private static final ArrayList<AbstractCardPack> packsPlayed = new ArrayList<>();

    public GemOfUnity() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT);
        resetCounter();
        description = getUpdatedDescription();
    }

    @Override
    public void onVictory() {
        resetCounter();
    }

    public void resetCounter(){
        if (AbstractDungeon.isPlayerInDungeon()) {
            packsPlayed.clear();
            counter = SpireAnniversary5Mod.currentPoolPacks.size();
            if (AbstractDungeon.player.hasRelic(BanishingDecree.ID)) counter--;
            this.description = getUpdatedDescription();
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (grayscale) {
            return;
        }
        AbstractCardPack pack = Wiz.getPackByCard(c);
        if (pack != null){
            if (!packsPlayed.contains(pack)){
                counter--;
                packsPlayed.add(pack);
                if (counter == 0){
                    AbstractDungeon.player.heal(5);
                    addToBot(new GainBlockAction(Wiz.p(), 20));
                    Wiz.applyToSelf(new StrengthPower(Wiz.p(), 1));
                    Wiz.applyToSelf(new DexterityPower(Wiz.p(), 1));
                    grayscale = true;
                }
            }
        }
        this.description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
        super.onPlayCard(c, m);
    }

    public String getUpdatedDescription() {
        StringBuilder desc = new StringBuilder(DESCRIPTIONS[0]);
        if (AbstractDungeon.isPlayerInDungeon()) {
            if (packsPlayed.size() > 0) {
                desc.append(" NL ");
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
