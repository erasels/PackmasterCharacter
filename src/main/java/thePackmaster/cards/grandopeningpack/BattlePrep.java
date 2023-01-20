package thePackmaster.cards.grandopeningpack;

import basemod.cardmods.InnateMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class BattlePrep extends AbstractGrandOpeningCard {
    public final static String ID = makeID("BattlePrep");

    public BattlePrep() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        FleetingField.fleeting.set(this, true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        atb(new SelectCardsInHandAction(this.cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            AbstractCard q = cards.get(0);
            CardModifierManager.addModifier(q, new InnateMod());

            AbstractDungeon.effectList.add(new UpgradeShineEffect(q.current_x, q.current_y));
            AbstractCard q2 = StSLib.getMasterDeckEquivalent(q);
            if (q2 != null){
                CardModifierManager.addModifier(q2, new InnateMod());
            }
        }));
    }

    @Override
    public void upp() {
        this.selfRetain = true;
    }
}