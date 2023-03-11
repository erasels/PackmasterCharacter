package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.packs.PixiePack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StardustShield extends AbstractPixieCard {
    public final static String ID = makeID("StardustShield");

    private static final int baseDef = 3;
    private static final int upgradeDef = 4;

    public StardustShield() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = this.block = baseDef;
        this.tags.add(PixiePack.pixieTags.ENCHANTMENT);
    }

    @Override
    public void upp() {
        this.upgradeBlock(upgradeDef-baseDef);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new GainBlockAction(abstractPlayer,block));
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (AbstractDungeon.player.hand.contains(this) && PixiePack.isForeign(c) && c.type==CardType.SKILL && !c.hasTag(PixiePack.pixieTags.ENCHANTMENT))
        {
            flash();
            AbstractCard toPlay = makeStatEquivalentCopy();
            addToBot(new EnchantmentAction(toPlay, m));
            AbstractDungeon.effectList.add(new LightningEffect(current_x,current_y));
        }
        super.onPlayCard(c, m);
    }
}
