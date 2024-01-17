package thePackmaster.cards.basicspack;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import thePackmaster.cardmodifiers.basicspack.BasicMod;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Strike;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicWave extends AbstractMultipleCardPreviewCard implements OnObtainCard{
    public final static String ID = makeID("BasicWave");

    public BasicWave() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "basics");
        if (CardCrawlGame.dungeon != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() instanceof MonsterRoom)
            CardModifierManager.addModifier(this, new BasicMod());
        this.cardToPreview.add(new Strike());
        this.cardToPreview.add(new Defend());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(AbstractCard c : this.cardToPreview)
            GameActionManager.queueExtraCard(c, m);
    }

    public void upp(){
        for(AbstractCard card : this.cardToPreview) {
            card.upgraded = false;
            card.upgrade();
            if (card.timesUpgraded > 1) {
                card.name = card.originalName + "+" + card.timesUpgraded;
            }
        }

    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            if(this.timesUpgraded>1) {
                String u = String.valueOf(this.timesUpgraded);
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + u + cardStrings.EXTENDED_DESCRIPTION[1] + u + cardStrings.EXTENDED_DESCRIPTION[2];
            } else this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.EXTENDED_DESCRIPTION[1] + cardStrings.EXTENDED_DESCRIPTION[2];
            initializeDescription();
        }
    }

    @Override
    public void onObtainCard() {
        CardModifierManager.addModifier(this, new BasicMod());
    }
}
