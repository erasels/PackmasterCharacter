package thePackmaster.cards.quantapack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import thePackmaster.actions.arcanapack.AllEnemyLoseHPAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cull extends AbstractQuantaCard {
    public final static String ID = makeID("Cull");

    public Cull() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL);
        baseBlock = 8;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));
        this.addToBot(new AllEnemyLoseHPAction(p,this.magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}
