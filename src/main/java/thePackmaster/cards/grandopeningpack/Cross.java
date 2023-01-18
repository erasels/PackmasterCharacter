package thePackmaster.cards.grandopeningpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.grandopeningpack.CrossPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cross extends AbstractPackmasterCard {
    public final static String ID = makeID("Cross");
    public Cross() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, "grandopening");
        this.baseDamage = this.damage = 2;
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractGameAction.AttackEffect slash = AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
        AbstractGameAction.AttackEffect other_slash = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
        AbstractGameAction.AttackEffect temp_slash;

        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), slash));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), other_slash));
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(CrossPower.POWER_ID.equals(p.ID)) {
                for (int i = 0; i < p.amount; i++) {
                    addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), slash));
                    temp_slash = slash;
                    slash = other_slash;
                    other_slash = temp_slash;
                }
            }
        }
        Wiz.applyToSelf(new CrossPower(abstractPlayer, 1));
        AbstractCard cross = new Cross();
        if(this.upgraded){
            cross.upgrade();
        }
        addToBot(new MakeTempCardInDrawPileAction(cross, 1, true, false));
    }

    public void applyPowers() {
        this.baseMagicNumber = 2;
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(CrossPower.POWER_ID.equals(p.ID)){
                this.baseMagicNumber += p.amount;
            }
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        if(this.upgraded)
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void upp() {
        this.isInnate = true;
    }
}