package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OverTime extends AbstractLegacyCard {
    public final static String ID = makeID("OverTime");

    private static final int ATTACK_PER_CARD = 4;
    private static final int UPGRADE_PLUS_DMG = 3;

    public OverTime() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 0;
        this.baseMagicNumber = ATTACK_PER_CARD;
        this.magicNumber = this.baseMagicNumber;
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!(this.baseDamage == 0)){
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public void atTurnStart() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size() * this.magicNumber;
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size() * this.magicNumber;
    }

    @Override
    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.exhaustPile.size() * this.magicNumber;
        super.applyPowers();
        initializeDescription();
    }

    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_DMG);
    }
}