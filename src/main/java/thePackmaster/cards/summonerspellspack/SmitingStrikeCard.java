package thePackmaster.cards.summonerspellspack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonerspellspack.SmitingStrikeAction;

public class SmitingStrikeCard extends AbstractSummonerSpellsCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SmitingStrike");
    private static final int COST = 1;
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public SmitingStrikeCard() {
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(CardTags.STRIKE);
        this.damage = this.baseDamage = DAMAGE;
    }

    @Override
    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SmitingStrikeAction(damage, m, p));
    }
}
