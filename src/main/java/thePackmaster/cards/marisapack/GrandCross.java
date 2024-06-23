package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import thePackmaster.patches.marisapack.AmplifyPatches;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GrandCross extends AbstractMarisaCard {
    public final static String ID = makeID(GrandCross.class.getSimpleName());
    private static final int DMG = 15, UPG_DMG = 5;
    private static final int MAGIC = 2;

    public GrandCross() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new ClashEffect(m.hb.cX, m.hb.cY), 0.1F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if(!AmplifyPatches.amplifiedThisTurn) {
            Wiz.atb(new DrawCardAction(magicNumber));
        }
    }

    @Override
    public boolean freeToPlay() {
        return super.freeToPlay() || AmplifyPatches.amplifiedThisTurn;
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
