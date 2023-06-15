package thePackmaster.cards.serpentinepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import thePackmaster.stances.serpentinepack.CunningStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Patience extends AbstractSerpentineCard {

    private static final int COST = 0;
    public final static String ID = makeID("Patience");
    private static final int BLOCK = 2;
    private static final int UPG_BLOCK = 2;

    public Patience() {
        super(ID, COST, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.selfRetain = true;
        this.baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ChangeStanceAction(new CunningStance()));
    }

    @Override
    public void onRetained(){
        addToBot(new GainBlockAction(AbstractDungeon.player, block));
    }

    @Override
    public void upp() {
        upgradeBlock(UPG_BLOCK);
    }
}
