package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.rimworldpack.KillThirstAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class KillThirst extends AbstractRimCard {
    public final static String ID = makeID(KillThirst.class.getSimpleName());

    public KillThirst() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        cardsToPreview = new Despair();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new KillThirstAction(this, m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

    public void upp() {
        upgradeDamage(2);
    }
}