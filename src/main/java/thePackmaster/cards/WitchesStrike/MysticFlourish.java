package thePackmaster.cards.WitchesStrike;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.CutThroughFate;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cardmodifiers.witchesstrikepack.InscribedMod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MysticFlourish extends AbstractWitchStrikeCard {
    public final static String ID = makeID("MysticFlourish");
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public MysticFlourish() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
        CardModifierManager.addModifier(this,new InscribedMod(true,true));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (upgraded){
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    if (AbstractDungeon.player.hand.size() > 0) {
                        ArrayList<AbstractCard> Uninscribed = new ArrayList<>();
                        for (AbstractCard c : AbstractDungeon.player.hand.group){
                            if (!CardModifierManager.hasModifier(c,"Inscribed")){
                                Uninscribed.add(c);
                            }
                        }
                        if (!Uninscribed.isEmpty()) {
                            AbstractCard targetCard = Uninscribed.get(AbstractDungeon.cardRandomRng.random(Uninscribed.size() - 1));
                            CardModifierManager.addModifier(targetCard, new InscribedMod(false,true));
                        }
                    }
                    isDone = true;
                }
            });
        }
    }

    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public String cardArtCopy() {
        return CutThroughFate.ID;
    }
}
