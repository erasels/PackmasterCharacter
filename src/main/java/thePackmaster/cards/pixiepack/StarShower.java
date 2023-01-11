package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.ThePackmaster;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import javax.swing.text.html.HTMLDocument;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StarShower extends AbstractPixieCard {
    public final static String ID = makeID("StarShower");

    private static final int baseAtk = 3;
    private static final int upgradeAtk = 4;

    public StarShower() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = baseAtk;
        this.tags.add(PixiePack.pixieTags.ENCHANTMENT);
    }

    @Override
    public void upp() {
        this.upgradeDamage(upgradeAtk-baseAtk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (AbstractDungeon.player.hand.contains(this) && PixiePack.isForeign(c) && c.type==CardType.ATTACK)
        {
            flash();
            AbstractCard toPlay = makeStatEquivalentCopy();
            addToBot(new EnchantmentAction(toPlay, m));
            AbstractDungeon.effectList.add(new LightningEffect(current_x,current_y));
        }
        super.onPlayCard(c, m);
    }
}
