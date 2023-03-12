package thePackmaster.actions.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

public class SimpleAddModifierAction extends AbstractGameAction {
    private AbstractCardModifier mod;
    private AbstractCard card;

    private boolean checkAgainstMadScience;

    public SimpleAddModifierAction(AbstractCardModifier mod, AbstractCard card) {
        this.mod = mod;
        this.card = card;
        this.checkAgainstMadScience = true;
    }

    public SimpleAddModifierAction(AbstractCardModifier mod, AbstractCard card, boolean checkAgainstIsCardModified) {
        this.mod = mod;
        this.card = card;
        this.checkAgainstMadScience = checkAgainstIsCardModified;
    }
    @Override
    public void update() {
        if (!isDone) {
            if (!checkAgainstMadScience || !card.hasTag(SpireAnniversary5Mod.ISCARDMODIFIED))
                CardModifierManager.addModifier(card, mod);
                card.superFlash(Color.CHARTREUSE.cpy());
            }

            isDone = true;
        }
    }
