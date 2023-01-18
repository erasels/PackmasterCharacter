package thePackmaster.cards.rimworldpack;

import basemod.interfaces.OnStartBattleSubscriber;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.actions.rimworldpack.HoldCardInMonsterAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TaintedWeapon extends AbstractRimCard implements StartupCard {
    public final static String ID = makeID(TaintedWeapon.class.getSimpleName());

    public TaintedWeapon() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 10;
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public void upp() {
        upgradeDamage(3);
    }

//    @Override
//    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
//        AbstractMonster target = AbstractDungeon.getRandomMonster();
//        if(target != null)
//            addToBot(new HoldCardInMonsterAction(target, this));
//    }


    @Override
    public void applyPowers()
    {
        getDesc();
        super.applyPowers();
    }
    @Override
    public void atTurnStart()
    {
        getDesc();
        super.atTurnStart();
    }

    @Override
    public void onMoveToDiscard()
    {
        getDesc();
    }

    private void getDesc()
    {
        rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        AbstractMonster target = AbstractDungeon.getRandomMonster();
        if(target != null)
            addToBot(new HoldCardInMonsterAction(target, this));
        return false;
    }
}