package thePackmaster.cards.startuppack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.startuppack.CrossPower;
import thePackmaster.util.Wiz;

import java.util.Objects;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cross extends AbstractStartUpCard {
    public final static String ID = makeID("Cross");
    public Cross() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 7;
        this.baseSecondDamage = this.secondDamage = 0;
        cardsToPreview = new Cross(true);
    }

    public Cross(boolean preview) {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = this.damage = 7;
        this.baseSecondDamage = this.secondDamage = 0;
        if(!preview)
            cardsToPreview = new Cross(true);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractMonster, this.secondDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        Wiz.applyToSelf(new CrossPower(abstractPlayer, 1));
        addToBot(new MakeTempCardInDrawPileAction(cardsToPreview, 1, true, false));
    }

    public void applyPowers() {
        this.baseSecondDamage = 0;
        for(AbstractPower p : AbstractDungeon.player.powers){
            if(CrossPower.POWER_ID.equals(p.ID)){
                this.baseSecondDamage += p.amount;
            }
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        if(this.upgraded){
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        }
        initializeDescription();
    }

    @Override
    public void upp() {
        this.isInnate = true;
        cardsToPreview.upgrade();
    }
}