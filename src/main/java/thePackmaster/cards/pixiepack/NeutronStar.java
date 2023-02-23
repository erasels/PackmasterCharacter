package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.actions.pixiepack.EnchantmentAction;
import thePackmaster.packs.PixiePack;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class NeutronStar extends AbstractPixieCard {
    public final static String ID = makeID("NeutronStar");

    private static final int baseMgk = 2;
    private static final int upgradeMgk = 3;

    public NeutronStar() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = baseMgk;
        this.tags.add(PixiePack.pixieTags.ENCHANTMENT);
        this.isEthereal = true;
        this.exhaust = true;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(upgradeMgk-baseMgk);
        this.isEthereal = false;
        this.exhaust = false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,magicNumber)));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new LoseStrengthPower(abstractPlayer,magicNumber)));
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
