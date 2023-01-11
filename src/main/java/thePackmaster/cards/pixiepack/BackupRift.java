package thePackmaster.cards.pixiepack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.pixiepack.DrawSpecificCardAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import javax.swing.text.html.HTMLDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BackupRift extends AbstractPackmasterCard {
    public final static String ID = makeID("BackupRift");

    private static final int baseDef = 12;
    private static final int upgradeDef = 15;
    private static final int baseMgk = 1;
    private static final int upgradeMgk = 2;

    public BackupRift() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        this.baseBlock = this.block = baseDef;
        this.baseMagicNumber = this.magicNumber = baseMgk;

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
        this.upgradeBlock(upgradeDef-baseDef);
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,block));
        ArrayList<AbstractCard> possibleCards = new ArrayList<>();
        for (AbstractCard C : AbstractDungeon.player.drawPile.group) {
            if (C.hasTag(PixiePack.pixieTags.ENCHANTMENT))
            {
                possibleCards.add(C);
            }
        }
        for (int i = 0; i < this.magicNumber; i++)
        {
            if (possibleCards.size()>0)
            {
                AbstractCard toDraw = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
                addToBot(new DrawSpecificCardAction(toDraw));
                possibleCards.remove(toDraw);
            }
        }
    }
}
