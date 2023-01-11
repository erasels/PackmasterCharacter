package thePackmaster.cards.warlockpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.AdditiveSlashImpactEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SoulShear extends AbstractPackmasterCard {
    public final static String ID = makeID(SoulShear.class.getSimpleName());

    private static final int COST = 1;

    public SoulShear() {
        super(ID, COST, AbstractCard.CardType.ATTACK, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        baseDamage = 7;
        cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new AdditiveSlashImpactEffect(m.hb.cX, m.hb.cY + 100.0F * Settings.scale, Color.PURPLE.cpy()));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new MakeTempCardInDrawPileAction(new Imp(), 1, true, true));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }
}
