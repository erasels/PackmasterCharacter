package thePackmaster.cards.bellordpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardColor.CURSE;
import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.vfx;

public class Clang extends AbstractBellordCard {
    public final static String ID = makeID("Clang");
    // intellij stuff attack, enemy, common, 14, 18, , , , 

    public Clang() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.player.hand.group.stream().anyMatch(q -> q.type == CardType.CURSE || q.type == CardType.STATUS || q.color == CURSE)) {
            cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }

    public void upp() {
        upgradeDamage(4);
    }
}