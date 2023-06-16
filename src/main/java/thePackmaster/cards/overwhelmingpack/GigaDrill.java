package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.jockeypack.Horse;
import thePackmaster.cards.sneckopack.Gulp;
import thePackmaster.powers.jockeypack.DerbyPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class GigaDrill extends AbstractOverwhelmingCard {
    public final static String ID = makeID("GigaDrill");

    public GigaDrill() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = this.damage = 12;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        int amt = magicNumber;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                int remaining = amt;
                for (int i = AbstractDungeon.player.drawPile.size() - 1; i >= 0 && remaining > 0; --i) {
                    AbstractCard c = AbstractDungeon.player.drawPile.group.get(i);
                    if (c.cost >= 0)
                        c.freeToPlayOnce = true;

                    --remaining;
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(4);
    }
}