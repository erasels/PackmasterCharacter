package thePackmaster.cardmodifiers.witchesstrikepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;
import thePackmaster.orbs.WitchesStrike.FullMoon;
import thePackmaster.util.Wiz;

public class InscribedMod extends AbstractCardModifier {
    private CardStrings uiStrings = CardCrawlGame.languagePack.getCardStrings(SpireAnniversary5Mod.makeID("Inscribed"));
    private boolean inherent = false;
    private boolean Todesc = true;

    public InscribedMod(boolean inherent, boolean todesc) {
        Todesc = todesc;
        this.inherent = inherent;
    }

    public boolean isInherent(AbstractCard card) {
        return inherent;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                if(!AbstractDungeon.player.orbs.isEmpty()) {
                    AbstractOrb o = AbstractDungeon.player.orbs.get(0);
                    if (!(o instanceof EmptyOrbSlot)) {
                        if (o instanceof PackmasterOrb) {
                            ((PackmasterOrb) o).passiveEffect();
                        } else {
                            o.onStartOfTurn();
                            o.onEndOfTurn();
                        }
                    }

                    for(AbstractOrb orb : Wiz.p().orbs) {
                        if(orb instanceof FullMoon || orb instanceof CrescentMoon) {
                            ((PackmasterOrb)orb).passiveEffect();
                        }
                    }
                }
                isDone = true;
            }
        });
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (Todesc) {
            return uiStrings.EXTENDED_DESCRIPTION[0] + rawDescription;
        } else return rawDescription;
    }
    @Override
    public void onInitialApplication(AbstractCard card) {
        card.tags.add(SpireAnniversary5Mod.ISCARDMODIFIED);
    }
    @Override
    public String identifier(AbstractCard card) {
        return "Inscribed";
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, "Inscribed");
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new InscribedMod(inherent, Todesc);
    }
}
