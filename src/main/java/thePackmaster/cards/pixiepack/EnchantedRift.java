package thePackmaster.cards.pixiepack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;
import thePackmaster.packs.PixiePack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EnchantedRift extends AbstractPixieCard {
    public final static String ID = makeID("EnchantedRift");

    private static final int baseDef = 12;
    private static final int upgradeDef = 15;

    public EnchantedRift() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = this.block = baseDef;
    }

    @Override
    public void upp() {
        this.upgradeBlock(upgradeDef - baseDef);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer, block));
        AbstractCard lastDifferent = null;
        for (int i = AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2; i >= 0; i--) {
            if (PixiePack.isForeign(AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i))) {
                lastDifferent = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(i);
                break;
            }
        }
        if (lastDifferent != null) {
            AbstractCard toAdd = lastDifferent.makeStatEquivalentCopy();
            CardModifierManager.addModifier(toAdd, new EtherealMod());
            addToBot(new MakeTempCardInHandAction(toAdd));
        }
    }
}
