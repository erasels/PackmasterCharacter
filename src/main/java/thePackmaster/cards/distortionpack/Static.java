package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.StaticAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Static extends AbstractDistortionCard {
    public final static String ID = makeID("Static");
    // intellij stuff attack, all_enemy, rare, 1, , , , 5, 2

    public Static() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 1;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i)
            atb(new StaticAction(p, this));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override //zhs card text thing
    public void initializeDescriptionCN() {
        super.initializeDescriptionCN();
        if (Settings.language == Settings.GameLanguage.ZHS) {
            StringBuilder first_line = new StringBuilder();
            this.description.get(0).text = first_line.append(this.description.get(0).text).append("。").toString();
            this.description.remove(1);
        }
    }
}