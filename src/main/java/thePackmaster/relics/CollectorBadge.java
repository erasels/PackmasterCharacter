package thePackmaster.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.SupportedLanguages;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CollectorBadge extends AbstractPackmasterRelic {
    public static final String ID = makeID("CollectorBadge");

    public ArrayList<String> usedPacks = new ArrayList<>();

    public CollectorBadge() {
        super(ID, RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        grayscale = true;
        setDescriptionAfterLoading();
    }

    @Override
    public void atBattleStart() {
        grayscale = true;
        setDescriptionAfterLoading();
    }

    @Override
    public void onVictory() {
        usedPacks.clear();
        grayscale = true;
        setDescriptionAfterLoading();
    }

    @Override
    public void atTurnStart() {
        if (!grayscale) {
            grayscale = true;
            flash();
            addToBot(new GainEnergyAction(1));
        }
        usedPacks.clear();
        setDescriptionAfterLoading();
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof AbstractPackmasterCard) {
            if (!usedPacks.contains(Wiz.getPackByCard(c).name)) {
                usedPacks.add(Wiz.getPackByCard(c).name);
                if (usedPacks.size() >= 3 && grayscale) {
                    this.grayscale = false;
                    flash();
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

            if (usedPacks.size()>1) {
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
