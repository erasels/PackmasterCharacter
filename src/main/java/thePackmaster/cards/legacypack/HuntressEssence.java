package thePackmaster.cards.legacypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HuntressEssence extends AbstractLegacyCard {
    public final static String ID = makeID("HuntressEssence");

    public static final int HEAL_AMT = 1;
    public static final int UPGRADE_PLUS_HEAL_AMT = 2;

    public HuntressEssence() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = HEAL_AMT;
        this.exhaustOnUseOnce = true;
        this.tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int exhausted = AbstractDungeon.player.exhaustPile.size();

        AbstractDungeon.actionManager.addToBottom(new HealAction(p,p,this.magicNumber * exhausted));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, exhausted));   }

    public void upp() {
        this.upgradeMagicNumber(UPGRADE_PLUS_HEAL_AMT);
    }
}