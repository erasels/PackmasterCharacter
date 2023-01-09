package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CollectorBadge extends AbstractPackmasterRelic {
    public static final String ID = makeID("CollectorBadge");

    public ArrayList<String> usedPacks = new ArrayList<>();

    public CollectorBadge() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        setDescriptionAfterLoading();
    }

    @Override
    public void atBattleStart() {
        setDescriptionAfterLoading();
        counter = 0;
    }

    @Override
    public void onVictory() {
        usedPacks.clear();
        setDescriptionAfterLoading();
        stopPulse();
        counter = -1;
    }

    @Override
    public void atTurnStart() {
        if (pulse) {
            counter = 0;
            flash();
            Wiz.atb(new RelicAboveCreatureAction(Wiz.p(), this));
            addToBot(new GainEnergyAction(1));
            stopPulse();
        }
        usedPacks.clear();
        setDescriptionAfterLoading();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (SpireAnniversary5Mod.cardParentMap.get(c.cardID) != null) {
            if (!usedPacks.contains(Wiz.getPackByCard(c).name)) {
                usedPacks.add(Wiz.getPackByCard(c).name);
                counter++;
                if (!pulse && usedPacks.size() >= 3) {
                    beginLongPulse();
                }
                setDescriptionAfterLoading();
            }
        }
    }

    private void setDescriptionAfterLoading() {

        if (usedPacks.size() > 2) {
            description = DESCRIPTIONS[0] + DESCRIPTIONS[2];
        } else if (usedPacks.size() > 0) {
            String st = usedPacks.get(0);

            if (usedPacks.size() > 1) {
                for (int i = 1; i < usedPacks.size(); i++) {
                    st = st + ", " + usedPacks.get(i);
                }
            }

            st = st + ".";
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + st;
        } else {
            description = DESCRIPTIONS[0];
        }
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

}
