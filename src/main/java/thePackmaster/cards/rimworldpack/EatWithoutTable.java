package thePackmaster.cards.rimworldpack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.SwiftPotion;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class EatWithoutTable extends AbstractRimCard {
    public final static String ID = makeID(EatWithoutTable.class.getSimpleName());

    public EatWithoutTable() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = 4;
        magicNumber = baseMagicNumber = 3;
        cardsToPreview = new Despair();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        blck();
        addToBot(new AddTemporaryHPAction(abstractPlayer, abstractPlayer, magicNumber));
        addToBot(new MakeTempCardInHandAction(new Despair()));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}