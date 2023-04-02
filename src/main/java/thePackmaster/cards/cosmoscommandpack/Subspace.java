package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import thePackmaster.powers.distortionpack.DistortionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.getEnemies;

public class Subspace extends AbstractCosmosCard {
    public final static String ID = makeID("Subspace");

    public Subspace() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : getEnemies()) {
            DistortionPower po = (DistortionPower) mo.getPower(DistortionPower.POWER_ID);
            if (po != null) {
                for (int i = 0; i < magicNumber; i++) {
                    // Had to rip this code out of DistortionPower after it was nerfed to reduce on activation
                    if (po.amount > 0) {
                        int amt = po.amount;
                        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                            @Override
                            public void update() {
                                if (!po.owner.isDeadOrEscaped())
                                    AbstractDungeon.effectList.add(new FlashPowerEffect(po));
                                this.isDone = true;
                            }
                        });
                        AbstractDungeon.actionManager.addToBottom(new DamageAction(po.owner, new DamageInfo(Wiz.p(), amt, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE, true));
                    }
                }
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}