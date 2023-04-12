package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BlueTearstone extends AbstractDarkSoulsCard {
    public final static String ID = makeID("BlueTearstone");
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public BlueTearstone() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().powers.stream()
                        .filter(p -> p.type == AbstractPower.PowerType.DEBUFF)
                        .forEach(p -> blck());
                isDone = true;
            }
        });

    }

    public void upp() {
        upgradeBlock(3);
    }
}