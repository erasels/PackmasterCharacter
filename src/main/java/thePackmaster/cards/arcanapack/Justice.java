package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Justice extends AbstractAstrologerCard {
    public final static String ID = makeID("Justice");
    // intellij stuff skill, none, common, , , 6, 2, , 

    public Justice() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseBlock = 7;

        AnimatedCardsPatch.loadFrames(this, 11, 0.14f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (!mo.isDeadOrEscaped()) {
                blck();
            }
        }

    }

    public void upp() {
        upgradeBlock(2);
    }
}