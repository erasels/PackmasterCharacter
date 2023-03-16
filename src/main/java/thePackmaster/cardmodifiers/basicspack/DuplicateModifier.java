package thePackmaster.cardmodifiers.basicspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class DuplicateModifier extends AbstractMadScienceModifier {
    private static final String[] description = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT;

    public DuplicateModifier(int valueIn){
        super(valueIn);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + description[0] + this.value + description[1];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if(card.purgeOnUse)
            return;
        for(int i = 1; i<this.value; i++){
            Wiz.atb(new RepeatCardAction(card.makeStatEquivalentCopy()));
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DuplicateModifier(value);
    }
}