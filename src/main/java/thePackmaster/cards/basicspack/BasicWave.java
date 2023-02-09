package thePackmaster.cards.basicspack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.scenes.scene2d.actions.RemoveAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.patches.relicInterfaces.OnRemoveCardFromMasterDeckPatch;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import thePackmaster.cardmodifiers.basicspack.BasicMod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Rummage;
import thePackmaster.cards.Strike;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicWave extends AbstractMultipleCardPreviewCard implements OnObtainCard {
    public final static String ID = makeID("BasicWave");

    public BasicWave() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, "basics");
        this.cardToPreview.add(new Strike());
        this.cardToPreview.add(new Defend());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i<this.cardToPreview.size(); i++){
            AbstractCard c = this.cardToPreview.get(i).makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(c);
            c.target_x = Settings.WIDTH / 2.0F - 150 * this.cardToPreview.size() + 300 * i;
            c.target_y = Settings.HEIGHT / 2.0F;
            if (m != null)
                c.calculateCardDamage(m);
            c.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, m, c.energyOnUse, true, true), true);
        }
    }

    @Override
    public void onObtainCard() {
        CardModifierManager.addModifier(this, new BasicMod());
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
}
