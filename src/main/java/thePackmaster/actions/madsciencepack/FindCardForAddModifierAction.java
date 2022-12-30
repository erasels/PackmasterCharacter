package thePackmaster.actions.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;

import java.util.ArrayList;
import java.util.function.Predicate;

import static thePackmaster.util.Wiz.atb;

public class FindCardForAddModifierAction extends AbstractGameAction {
    private AbstractCardModifier mod;
    private int count;
    private boolean random;
    private CardGroup targetgroup;
    private Predicate<AbstractCard> requirements;

    public FindCardForAddModifierAction(AbstractCardModifier mod, int count, boolean random, CardGroup targetgroup) {
        this.mod = mod;
        this.count = count;
        this.random = random;
        this.targetgroup = targetgroup;
        this.requirements = null;
    }

    public FindCardForAddModifierAction(AbstractCardModifier mod, int count, boolean random, CardGroup targetgroup, Predicate<AbstractCard> requirements) {
        this.mod = mod;
        this.count = count;
        this.random = random;
        this.targetgroup = targetgroup;
        this.requirements = requirements;

    }

    @Override
    public void update() {
        if (!isDone){
            Predicate<AbstractCard> combinedRequirements = card -> !card.hasTag(SpireAnniversary5Mod.ISCARDMODIFIED);

            if (requirements != null){
                combinedRequirements = combinedRequirements.and(requirements);
            }

            if (random) {
                ArrayList<AbstractCard> chosen = new ArrayList<>();
                AbstractCard n;
                ArrayList<AbstractCard> potentialTargets = new ArrayList<>(targetgroup.group);
                for (int i = 0; i < count; i++) {
                    n = potentialTargets.get(AbstractDungeon.cardRandomRng.random(0, potentialTargets.size()-1));
                    potentialTargets.remove(n);
                    if (combinedRequirements.test(n)){
                        chosen.add(n);
                    } else {
                        i--;
                        if (potentialTargets.isEmpty()){
                            return;
                        }
                    }
                }

                for (AbstractCard c2 : chosen
                ) {
                    CardModifierManager.addModifier(c2, mod);
                    c2.superFlash(Color.CHARTREUSE);
                }
            } else {
                atb(new SelectCardsAction(targetgroup.group, count, " to Modify", false,
                        combinedRequirements,

                        (cards) -> {
                            for (AbstractCard c2 : cards
                            ) {
                                CardModifierManager.addModifier(c2, mod);
                                c2.superFlash(Color.CHARTREUSE);
                            }
                        }
                ));
            }

        }
        isDone = true;
    }
}