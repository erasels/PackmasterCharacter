package thePackmaster.cards.quietpack;


import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.quietpack.BackflipFrontflipPatch;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Frontflip extends AbstractQuietCard {
    public final static String ID = makeID("Frontflip");

    public Frontflip() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 13;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Loader.isModLoadedOrSideloaded("backflip")) {
            try {
                Class<?> actionClass = Class.forName("silentBackflip.ThingActionFuck");
                AbstractGameAction action = (AbstractGameAction) actionClass.newInstance();
                BackflipFrontflipPatch.Field.isFrontflip.set(action, true);
                addToBot(action);
            } catch (Exception ignored) {}
        }
        blck();
        addToBot(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.att(new WaitAction(0.4F));
                DrawCardAction.drawnCards.stream().filter(c -> c.cost == -2).forEach(c -> Wiz.att(new DiscardSpecificCardAction(c)));
                isDone = true;
            }
        }));
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upMagic(1);
    }
}


