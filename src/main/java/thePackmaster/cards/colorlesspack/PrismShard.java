package thePackmaster.cards.colorlesspack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.colorlesspack.PrismAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.colorlesspack.ColoredVerticalAttackEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class PrismShard extends AbstractPackmasterCard {
    public final static String ID = makeID("PrismShard");
    // intellij stuff attack, enemy, special, 8, 1, , , , 

    private boolean preview = false;

    public PrismShard(boolean showPreview) {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ALL_ENEMY, CardColor.COLORLESS);
        baseDamage = 8;
        exhaust = true;
        if (showPreview) cardsToPreview = new ThePrism(false);
        this.preview = showPreview;
    }

    public PrismShard() {
        this(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new ColoredVerticalAttackEffect(m.hb.x + MathUtils.random(m.hb.width / 3, ((m.hb.width / 3) * 2)), m.hb.cY, true, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1))));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrismShard(preview);
    }
}