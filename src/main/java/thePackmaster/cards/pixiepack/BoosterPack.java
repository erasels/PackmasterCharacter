package thePackmaster.cards.pixiepack;

import basemod.cardmods.EtherealMod;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.PixiePack;

import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BoosterPack extends AbstractPackmasterCard {
    public final static String ID = makeID("BoosterPack");

    private static final int baseMagic = 3;
    private static final int upgradeMagic = 5;

    public BoosterPack() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, ThePackmaster.Enums.PACKMASTER_RAINBOW);
        this.magicNumber = this.baseMagicNumber = baseMagic;

        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(upgradeMagic-baseMagic);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for(int i = 0; i < this.magicNumber; i++)
        {
            AbstractCard toAdd = PixiePack.pixieGenerate(null,null,null);
            CardModifierManager.addModifier(toAdd,new EtherealMod());
            addToBot(new MakeTempCardInHandAction(toAdd));
        }
    }
}
