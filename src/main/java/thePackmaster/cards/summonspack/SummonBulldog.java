package thePackmaster.cards.summonspack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.summonspack.Bulldog;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SummonBulldog extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonBulldog.class.getSimpleName());
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 1;

    public SummonBulldog() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChannelAction(new Bulldog(magicNumber)));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
