package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.grandopeningpack.CrossPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cross extends AbstractGrandOpeningCard {
    public final static String ID = makeID("Cross");
    public Cross() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 2;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractMonster != null)
            addToBot(new VFXAction(new ClashEffect(abstractMonster.hb.cX, abstractMonster.hb.cY), 0.1F));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        Wiz.applyToSelf(new CrossPower(abstractPlayer, 1));
        AbstractCard cross = new Cross();
        if(this.upgraded){
            cross.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(cross, 1, true, false));
    }

    public void applyPowers() {
        this.baseDamage = 2;
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(CrossPower.POWER_ID.equals(p.ID)){
                this.baseDamage += p.amount;
                this.isDamageModified = true;
            }
        }
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}