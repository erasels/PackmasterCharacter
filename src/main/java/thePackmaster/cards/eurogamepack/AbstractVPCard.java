package thePackmaster.cards.eurogamepack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.eurogamepack.RoadbuildingPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import thePackmaster.powers.eurogamepack.VictoryPoints;

import static thePackmaster.util.Wiz.atb;




public abstract class AbstractVPCard extends AbstractPackmasterCard {

    public AbstractVPCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        super(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        setBackgroundTexture(
                "anniv5Resources/images/512/eurogame/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/eurogame/" + type.name().toLowerCase() + ".png"
        );
    }



    public void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }



    public void applyPowers() {
        super.applyPowers();
        int base = this.baseSecondMagic;
        if (AbstractDungeon.player.hasPower(RoadbuildingPower.POWER_ID)) {
            base += AbstractDungeon.player.getPower(RoadbuildingPower.POWER_ID).amount;
        }


        this.secondMagic = base;
        this.isSecondMagicModified = this.secondMagic != this.baseSecondMagic;
    }
}
