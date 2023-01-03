package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RelentlessHail extends AbstractPackmasterCard {
    public final static String ID = makeID("RelentlessHail");

    public RelentlessHail() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = 4;
        magicNumber = baseMagicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new BlizzardEffect(magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
        } else {
            this.addToBot(new VFXAction(new BlizzardEffect(magicNumber, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
        }

        for (int i = 0; i < magicNumber; i++)
            dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}