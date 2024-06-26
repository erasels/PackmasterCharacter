package thePackmaster.commands;

import basemod.DevConsole;
import basemod.devcommands.ConsoleCommand;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.hats.HatMenu;
import thePackmaster.packs.AbstractCardPack;

import java.util.ArrayList;

public class UnlockHatCommand extends ConsoleCommand {
    public UnlockHatCommand() {
        minExtraTokens = 1;
        simpleCheck = true;
    }

    @Override
    public void execute(String[] tokens, int depth) {
        if (tokens.length != 2) {
            DevConsole.log("You need to add the name of the pack of the hat you want.");
            return;
        }

        ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();
        ArrayList<String> unseenHats = SpireAnniversary5Mod.getUnseenHats();

        if (tokens[1].equalsIgnoreCase("all")) {
            boolean addedHats = false;
            for (AbstractCardPack pack : SpireAnniversary5Mod.unfilteredAllPacks) {
                addedHats = addedHats || addHatIfNotPresent(pack.packID, unlockedHats, unseenHats);
            }
            if (addedHats) {
                saveHats(unlockedHats, unseenHats);
            }
            return;
        }

        boolean found = false;
        String id = "";
        for (AbstractCardPack pack : SpireAnniversary5Mod.unfilteredAllPacks) {
            if (pack.name.equals(tokens[1].replace("_", " "))) {
                found = true;
                id = pack.packID;
                break;
            }
        }

        if (found) {
            if (addHatIfNotPresent(id, unlockedHats, unseenHats)) {
                saveHats(unlockedHats, unseenHats);
            }
        } else {
            errorMsg();
        }
    }

    private boolean addHatIfNotPresent(String hatId, ArrayList<String> unlockedHats, ArrayList<String> unseenHats) {
        boolean shouldAddHat = !unlockedHats.contains(hatId);
        if (shouldAddHat) {
            try {
                unlockedHats.add(hatId);
                unseenHats.add(hatId);
                SpireAnniversary5Mod.logger.info("Saving hat " + hatId + ", acquired via console.");
            } catch (Exception e) {
                SpireAnniversary5Mod.logger.error(e);
            }
        }
        return shouldAddHat;
    }

    private void saveHats(ArrayList<String> unlockedHats, ArrayList<String> unseenHats) {
        try {
            SpireAnniversary5Mod.saveUnlockedHats(unlockedHats);
            SpireAnniversary5Mod.saveUnseenHats(unseenHats);
            HatMenu.refreshHatDropdown();
        } catch (Exception e) {
            SpireAnniversary5Mod.logger.error(e);
        }
    }

    @Override
    public ArrayList<String> extraOptions(String[] tokens, int depth) {
        ArrayList<String> result = new ArrayList<>();
        result.add("all");
        ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();
        SpireAnniversary5Mod.unfilteredAllPacks.stream()
                .filter(p -> !unlockedHats.contains(p.packID))
                .forEach(p -> result.add(p.name.replace(" ", "_")));

        return result;
    }

    @Override
    public void errorMsg() {
        DevConsole.couldNotParse();
        DevConsole.log("Please put in a valid pack name the hat is from.");
    }
}