package thePackmaster.cards.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitzBolt extends AbstractEvenOddCard{
    public final static String ID = makeID(WitzBolt.class.getSimpleName());
    private static final int DAMAGE = 8;
    private static final int UDAMAGE = 3;
    private static final int MAGIC = 1;
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    
    public WitzBolt() {
        super(ID, COST, TYPE, RARITY, TARGET);
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }
    
    @Override
    public void upp() {
        upgradeDamage(UDAMAGE);
    }
    
    @Override
    public void onMoveToDiscard() {
        rawDescription = cardStrings.DESCRIPTION;
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
        initializeDescription();
    }
    
    @Override
    protected String createEvenOddText() {
        if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0)
        {
            return   cardStrings.DESCRIPTION
                + cardStrings.EXTENDED_DESCRIPTION[0]
                + cardStrings.EXTENDED_DESCRIPTION[2];
        }
        else
        {
            return cardStrings.DESCRIPTION;
        }
    }
    
    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(abstractMonster, AbstractGameAction.AttackEffect.NONE);
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1) {
                    Wiz.applyToEnemyTop(abstractMonster, new VulnerablePower(abstractMonster, magicNumber, false));
                    Wiz.applyToEnemyTop(abstractMonster, new WeakPower(abstractMonster, magicNumber, false));
                    AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CHAMP_2A", 0.5f, true));
                    for(int i = 0; i <5; i++) {
                        addToBot(new VFXAction(new StarBounceEffect(abstractMonster.hb.cX, abstractMonster.hb.cY), 0.05F));
                    }
                }
                this.isDone = true;
            }
        });
        addToBot(new VFXAction(new LightningEffect(abstractMonster.drawX, abstractMonster.drawY), 0.05F));
    }
}
