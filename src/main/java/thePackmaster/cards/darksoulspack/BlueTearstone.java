package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

import java.util.concurrent.atomic.AtomicInteger;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlueTearstone extends AbstractDarkSoulsCard {
    public final static String ID = makeID("BlueTearstone");
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public BlueTearstone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AtomicInteger count= new AtomicInteger(1);
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().powers.stream()
                        .filter(p -> p.type == AbstractPower.PowerType.DEBUFF)
                        .forEach(p -> count.getAndIncrement());
                isDone = true;
            }
        });
        for (int i = 0; i < count.get(); i++) {
            blck();
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}