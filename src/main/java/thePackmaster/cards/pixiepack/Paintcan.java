package thePackmaster.cards.pixiepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.pixiepack.PaintcanModifier;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Paintcan extends AbstractPixieCard {
    public final static String ID = makeID("Paintcan");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    private static final int baseAtk = 9;
    private static final int upgradeAtk = 12;

    public Paintcan() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = this.damage = baseAtk;
    }

    @Override
    public void upp() {
        upgradeDamage(upgradeAtk - baseAtk);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        dmg(Wiz.getRandomEnemy(), AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            CardModifierManager.addModifier(c, new PaintcanModifier(0));
        }
    }
}
