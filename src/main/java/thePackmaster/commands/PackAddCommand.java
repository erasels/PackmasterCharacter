package thePackmaster.commands;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class PackAddCommand  extends ConsoleCommand {
    public PackAddCommand() {
        minExtraTokens = 1;
        simpleCheck = true;
        requiresPlayer = true;
    }

    @Override
    public void execute(String[] tokens, int depth) {
        if(!Wiz.isInCombat()) {
            DevConsole.log("You can only do this in combat.");
            return;
        }
        if (tokens.length != 2) {
            DevConsole.log("You need to add the name of the pack you want.");
            return;
        }
        boolean found = false;
        String id = "";
        for(AbstractCardPack pack : SpireAnniversary5Mod.unfilteredAllPacks) {
            if(pack.name.equals(tokens[1].replace("_", " "))) {
                found = true;
                id = pack.packID;
                break;
            }
        }

        if(found) {
            Wiz.atb(new DiscardAction(AbstractDungeon.player, AbstractDungeon.player, Wiz.hand().size(), true, false));
            for (String s : SpireAnniversary5Mod.packsByID.get(id).getCards()) {
                AbstractCard c = CardLibrary.getCard(s);
                if(c.rarity == AbstractCard.CardRarity.COMMON || c.rarity == AbstractCard.CardRarity.UNCOMMON || c.rarity == AbstractCard.CardRarity.RARE) {
                    Wiz.atb(new MakeTempCardInHandAction(c));
                }
            }
        } else {
            errorMsg();
        }
    }

    @Override
    public ArrayList<String> extraOptions(String[] tokens, int depth) {
        ArrayList<String> result = new ArrayList<>();
        SpireAnniversary5Mod.unfilteredAllPacks
                .forEach(p -> result.add(p.name.replace(" ", "_")));

        return result;
    }

    @Override
    public void errorMsg() {
        DevConsole.couldNotParse();
        DevConsole.log("Please put in a valid pack name.");
    }
}